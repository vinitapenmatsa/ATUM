package com.eyedentify.vinitapenmatsa.atum.Panels;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.eyedentify.vinitapenmatsa.atum.R;
import com.eyedentify.vinitapenmatsa.atum.models.Panel;

/**
 * A simple {@link Fragment} subclass.
 */
public class PanelFragment extends Fragment {

    public GridView panelGrid;

    private Panel[] panels;


    public PanelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_panel,container,false);

        Panel panel1 = new Panel(23.4,32.3,"");
        Panel panel2 = new Panel(32.4,12.4,"TOO_LOW");
        Panel panel3 = new Panel(23.4,32.3,"");
        Panel panel4 = new Panel(32.4,12.4,"TOO_LOW");
        Panel panel5 = new Panel(23.4,32.3,"");
        Panel panel6 = new Panel(32.4,12.4,"TOO_LOW");

        Panel[] panels = {panel1,panel2,panel3,panel4,panel5,panel6};

        panelGrid = view.findViewById(R.id.panelGrid);
        panelGrid.setAdapter(new PanelGridAdapter(getContext(),panels));

        return view;
    }

}
