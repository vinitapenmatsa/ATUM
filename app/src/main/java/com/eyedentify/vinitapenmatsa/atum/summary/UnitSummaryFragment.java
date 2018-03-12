package com.eyedentify.vinitapenmatsa.atum.summary;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eyedentify.vinitapenmatsa.atum.R;
import com.eyedentify.vinitapenmatsa.atum.models.SummaryGridItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class UnitSummaryFragment extends Fragment {

    private RecyclerView mRecyclerGridView;
    private RecyclerView.Adapter mSummaryGridAdapter;
    private RecyclerView.LayoutManager mLayoutGridManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unit_summary,container,false);

        ArrayList<SummaryGridItem> summaryGridItems = new ArrayList<SummaryGridItem>();

        summaryGridItems.add(new SummaryGridItem("Generated" , R.drawable.solar_panel , 10.0 ));
        summaryGridItems.add(new SummaryGridItem("consumed", R.drawable.house , 10.0));
        summaryGridItems.add(new SummaryGridItem("Battery Level" , R.drawable.battery , 10.0));
        summaryGridItems.add(new SummaryGridItem("Sent to Grid" , R.drawable.tower , 10.0));

        mRecyclerGridView = (RecyclerView)view.findViewById((R.id.unit_summary_grid));
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerGridView.setHasFixedSize(true);

        mLayoutGridManager = new GridLayoutManager(getContext(),2);
        mRecyclerGridView.setLayoutManager(mLayoutGridManager);

        mSummaryGridAdapter = new SummaryGridAdapter(summaryGridItems);
        mRecyclerGridView.setAdapter(mSummaryGridAdapter);



        return view;
    }

    protected class SummaryGridAdapter extends RecyclerView.Adapter<SummaryGridAdapter.SummaryViewHolder>{

        ArrayList<SummaryGridItem> summaryGridItems;

        public class SummaryViewHolder extends RecyclerView.ViewHolder{

            TextView summaryItemLabel;
            TextView summaryItemValue;
            ImageView summaryItemImage;

            public SummaryViewHolder(View view){
                super(view);

                summaryItemLabel = (TextView) view.findViewById(R.id.summary_grid_label);
                summaryItemValue = (TextView)view.findViewById(R.id.summary_grid_value);
                summaryItemImage = (ImageView) view.findViewById(R.id.summary_grid_image);

            }
        }

        public SummaryGridAdapter(ArrayList<SummaryGridItem> summaryGridItems){
            this.summaryGridItems = summaryGridItems;
        }

        @Override
        public SummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_unit_summary, parent, false);

            return new SummaryViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SummaryViewHolder holder, int position) {

            SummaryGridItem summaryGridItem = (SummaryGridItem)summaryGridItems.get(position);

            holder.summaryItemLabel.setText(summaryGridItem.getLabel());
            holder.summaryItemValue.setText(String.valueOf(summaryGridItem.getValue()));
            holder.summaryItemImage.setImageResource(summaryGridItem.getResourceId());

        }

        @Override
        public int getItemCount() {
            return summaryGridItems.size();
        }


    }

}
