package com.eyedentify.vinitapenmatsa.atum.summary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eyedentify.vinitapenmatsa.atum.R;

/**
 * Created by vinitapenmatsa on 1/8/18.
 */

public class CurrentPowerAdapter extends BaseAdapter {

    private Context context;
    private String[] currentPower;


    public CurrentPowerAdapter(Context context,String[] currentPower){
        this.context = context;
        this.currentPower = currentPower;
    }

    @Override
    public int getCount(){ return 2;};

    @Override
    public Object getItem(int position) { return null;}

    @Override
    public long getItemId(int position) {return 0;}

    @Override
    public View getView(int position , View convertView , ViewGroup parent){

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.fragment_summary_current_power,null);

        TextView currentPowerLabel = convertView.findViewById(R.id.currentPower);

        currentPowerLabel.setText(currentPower[position]); //TODO move this to strings xml

        convertView.setBackgroundColor(context.getResources().getColor( R.color.colorSummaryGrid));


        return convertView;
    }


}
