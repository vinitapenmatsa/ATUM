package com.eyedentify.vinitapenmatsa.atum.summary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eyedentify.vinitapenmatsa.atum.R;

/**
 * Created by vinitapenmatsa on 1/5/18.
 */

public class SummaryGridAdapter extends BaseAdapter {

    private Context context;
    private String[] summaryItems;
    private int[] summaryItemIcons;

    public SummaryGridAdapter(Context context , String[] summaryItems , int[] summaryItemIcons){
        this.context = context;
        this.summaryItems = summaryItems;
        this.summaryItemIcons = summaryItemIcons;
    }

    @Override
    public int getCount(){
        return  summaryItems.length;
    }

    @Override
    public Object getItem(int position) { return null;}

    @Override
    public long getItemId(int position) {return 0;}

    @Override
    public View getView(int position, View convertView , ViewGroup parent){

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.fragment_summary_grid,null);
        ImageView summaryIcon = (ImageView)convertView.findViewById(R.id.summaryIcon);
        TextView summaryReading = (TextView)convertView.findViewById(R.id.summaryReading);


        summaryIcon.setImageResource(summaryItemIcons[position]);
        summaryReading.setText(summaryItems[position]);

        convertView.setBackgroundColor( context.getResources().getColor( R.color.colorSummaryGrid));

        return convertView;


    }
}
