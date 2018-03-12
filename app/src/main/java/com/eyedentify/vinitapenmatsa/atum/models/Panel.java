package com.eyedentify.vinitapenmatsa.atum.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by vinitapenmatsa on 1/3/18.
 */

@IgnoreExtraProperties
public class Panel {

    public String id;
    public double voltage;
    public double power;
    public String status;


    public Panel(){};

    public Panel(double voltage , double power , String status){
        this.voltage = voltage;
        this.power = power;
        this.status = status;
    };



}
