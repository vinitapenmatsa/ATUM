package com.eyedentify.vinitapenmatsa.atum.summary;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Fragment;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.eyedentify.vinitapenmatsa.atum.R;
import com.eyedentify.vinitapenmatsa.atum.models.SummaryGridItem;
import com.eyedentify.vinitapenmatsa.atum.service.WebSocketService;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ua.naiksoftware.stomp.client.StompClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

   /* public GridView summaryGrid;
    public GridView summaryTotalsGrid; //displays power generated per day / month / lifetime
    public GridView currentPowerGrid; // displays current power generated
    public GraphView powerGenerationGraph; */

    private ArrayList<SummaryGridItem> summaryGridItems;

   /* private String[] summaryItems;
    private int[] summaryItemIcons = {R.drawable.solar_panel , R.drawable.house , R.drawable.battery , R.drawable.tower};

    private String[] totalsLabels = { "Today" , "This Month" , "Lifetime"};
    private String[] totalsValues = {"2 Wh" , "11.81 KWh" , "6.84 MWh"}; // change this to realtime values


    //Web Scoket connection to consume real-time information
    private StompClient stompClient;
    private WebSocketService webSocketService;
    private Boolean isWebSocketServiceBound = false; */

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary,container,false);

        summaryGridItems = getSummaryGrid();

        /*summaryItems = getResources().getStringArray(R.array.summary_items);

        summaryGrid = view.findViewById(R.id.summaryGrid);
        summaryGrid.setAdapter(new SummaryGridAdapter(getContext() , summaryItems, summaryItemIcons));


        //Grid that display totoal poewr genrattion for the - day / month / lifetime

        summaryTotalsGrid = view.findViewById(R.id.totalsGrid);
        summaryTotalsGrid.setAdapter(new SummaryTotalsAdapter(getContext(),totalsLabels,totalsValues ));

        //Monthly Power Generation Graph

        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();

        powerGenerationGraph = (GraphView) view.findViewById(R.id.powerGenerationGraph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 1),
                new DataPoint(2, 5),
                new DataPoint(3, 3),
                new DataPoint(4, 4),
                new DataPoint(5, 5)

        });
        series.setSpacing(80);
        series.setTitle("Day");
        powerGenerationGraph.addSeries(series);
        //powerGenerationGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
       // powerGenerationGraph.getGridLabelRenderer().setNumHorizontalLabels(3);
        powerGenerationGraph.setBackgroundColor(getContext().getResources().getColor( R.color.colorSummaryGrid));


        //current Power generated
        currentPowerGrid = view.findViewById(R.id.currentPowerGrid);
        String[] currenPower = { "Current Power" , "10 Kw"};
        currentPowerGrid.setAdapter(new CurrentPowerAdapter(getContext(),currenPower)); */



        // Inflate the layout for this fragment
        return view;
    }


    public ArrayList<SummaryGridItem> getSummaryGrid(){

        ArrayList<SummaryGridItem> summaryGridItems = new ArrayList<SummaryGridItem>();

        summaryGridItems.add(new SummaryGridItem("Generated" , R.drawable.solar_panel , 10.0 ));
        summaryGridItems.add(new SummaryGridItem("consumed", R.drawable.house , 10.0));
        summaryGridItems.add(new SummaryGridItem("Battery Level" , R.drawable.battery , 10.0));
        summaryGridItems.add(new SummaryGridItem("Sent to Grid" , R.drawable.tower , 10.0));

        return summaryGridItems;


    }


}
