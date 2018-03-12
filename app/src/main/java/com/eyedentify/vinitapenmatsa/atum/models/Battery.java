package com.eyedentify.vinitapenmatsa.atum.models;

/**
 * Created by vinitapenmatsa on 1/3/18.
 */

public class Battery {

    public String id;
    public double voltage;
    public double SOC;
    public String notes;

    public Battery(){};

    public Battery(double voltage , double SOC , String notes){
        this.voltage = voltage;
        this.SOC = SOC;
        this.notes = notes;

    }

}
