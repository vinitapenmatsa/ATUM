package com.eyedentify.vinitapenmatsa.atum;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toolbar;

import com.eyedentify.vinitapenmatsa.atum.Panels.PanelFragment;
import com.eyedentify.vinitapenmatsa.atum.models.SummaryGridItem;
import com.eyedentify.vinitapenmatsa.atum.summary.SummaryFragment;
import com.eyedentify.vinitapenmatsa.atum.summary.UnitSummaryFragment;
import com.eyedentify.vinitapenmatsa.atum.utils.SetupUtils;

import java.util.ArrayList;

import static com.google.android.gms.common.ConnectionResult.TIMEOUT;

public class MainActivity extends WifiBaseActivity implements WifiBase.WifiBaseListener {

    private static final int TIMEOUT = 30;
    private WifiBase mWifiBase;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolBar;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private String[] mMenuTitles;

    private TcpClient dataClient;


    @Override
    public String getWifiSSID() {
        return "AI-THINKER_3A070C";
    }

    @Override
    public String getWifiPass() {
        return "";
    }

    @Override
    public int getSecondsTimeout() {
        return TIMEOUT;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO Establish wifi connection and then open TCP connection
        //mWifiBase = new WifiBase(this);
        //new ConnectTask().execute("");

        //Setup test data for the time being until we get realtime data
        SetupUtils.setupData();

        mTitle = mDrawerTitle = getTitle();
        mMenuTitles = getResources().getStringArray(R.array.menu_options);
        mDrawerLayout =  (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.left_drawer);

        // set a custom shadow when drawer overlays the main content
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        //set drawers menu list
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item , mMenuTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Define Action bar toggle

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ){
            public void onDrawerClosed(View view){
                getSupportActionBar().setTitle(getTitle());
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);


       if (savedInstanceState == null) {
            selectItem(0);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle action buttons
        switch(item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPause() {
        super.onPause();

        // disconnect
        dataClient.stopClient();
        dataClient = null;

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void  onItemClick(AdapterView<?> parent , View view , int position , long id){
            selectItem(position);
        }

    }

    private void selectItem(int position){

        //implement logic to replace appropriate fragments and close

        switch(mMenuTitles[position]){

            case "Summary":
               SummaryFragment();
               break;
            case "Panels":
                PanelFragment();
                break;

                default:
                    break;


        }


        //update title with selected item and close the drawer
        mDrawerList.setItemChecked(position,true);
        setTitle(mMenuTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    public class ConnectTask extends AsyncTask<String, String, TcpClient> {

        @Override
        protected TcpClient doInBackground(String... message) {

            //we create a TCPClient object and
            dataClient = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    Log.i("TCP Callback", message);
                    publishProgress(message);
                }
            });
            dataClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            //in the arrayList we add the messaged received from server
            Log.i("TCP CALLBACK", values[0]);
            // notify the adapter that the data set has changed. This means that new message received
            // from server was added to the list
            //mAdapter.notifyDataSetChanged();
        }
    }

    private void SummaryFragment(){

        Fragment fragment = new UnitSummaryFragment();
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame , fragment ).commit();

    }

    private void PanelFragment(){
        Fragment fragment = new PanelFragment();
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame , fragment).commit();
    }
}
