package com.eyedentify.vinitapenmatsa.atum.summary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eyedentify.vinitapenmatsa.atum.R;

/**
 * Created by vinitapenmatsa on 1/6/18.
 */

public class SummaryTotalsAdapter extends BaseAdapter {

    private Context context;
    private String[] totalsLabels;
    private String[] totalsValues;

    public SummaryTotalsAdapter(Context context, String[] totalsLabels, String[] totalsValues){
        this.context = context;
        this.totalsLabels = totalsLabels;
        this.totalsValues = totalsValues;
    }

    @Override
    public int getCount(){ return totalsLabels.length;};

    @Override
    public Object getItem(int position) { return null;}

    @Override
    public long getItemId(int position) {return 0;}

    @Override
    public View getView(int position , View convertView , ViewGroup parent){

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.fragment_summary_totals,null);

        TextView totalsLabel = (TextView)convertView.findViewById(R.id.totalsLabel);
        TextView totalsValue = (TextView)convertView.findViewById(R.id.totalsValue);

        totalsLabel.setText(totalsLabels[position]);
        totalsValue.setText(totalsValues[position]);

        convertView.setBackgroundColor( context.getResources().getColor( R.color.colorSummaryGrid));

        return convertView;


    }




}
