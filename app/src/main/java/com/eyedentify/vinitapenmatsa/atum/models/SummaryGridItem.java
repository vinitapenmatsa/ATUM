package com.eyedentify.vinitapenmatsa.atum.models;

/**
 * Created by vinitapenmatsa on 2/12/18.
 */

public class SummaryGridItem {

    private String label;
    private int resourceId;
    private double value;

    public SummaryGridItem(){};

    public SummaryGridItem(String label, int resourceId , double value){
        this.label = label;
        this.resourceId = resourceId;
        this.value = value;
    };


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
