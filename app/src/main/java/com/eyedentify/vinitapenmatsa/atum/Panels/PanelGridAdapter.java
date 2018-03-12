package com.eyedentify.vinitapenmatsa.atum.Panels;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.eyedentify.vinitapenmatsa.atum.R;
import com.eyedentify.vinitapenmatsa.atum.models.Panel;

/**
 * Created by vinitapenmatsa on 1/9/18.
 */

public class PanelGridAdapter extends BaseAdapter {

    private Context context;
    private Panel[] panels;

    public PanelGridAdapter(Context context, Panel[] panels){
        this.context = context;
        this.panels = panels;
    }

    @Override
    public int getCount(){
        return  panels.length;
    }

    @Override
    public Object getItem(int position) { return null;}

    @Override
    public long getItemId(int position) {return 0;}

    @Override
    public View getView(int position, View convertView , ViewGroup parent){

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.fragment_panel_item,null);

        ImageView panelItem = (ImageView)convertView.findViewById(R.id.panelRectangle);
        panelItem.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.panel_item,null));


        return convertView;

    }


}
