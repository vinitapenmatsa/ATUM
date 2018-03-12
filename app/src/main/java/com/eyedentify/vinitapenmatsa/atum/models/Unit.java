package com.eyedentify.vinitapenmatsa.atum.models;

import java.util.List;

/**
 * Created by vinitapenmatsa on 1/3/18.
 */

public class Unit {

    public List<Panel> panels;
    public List<Battery> battery;

    public Unit(){};
    public Unit( List<Panel> panels , List<Battery> battery){
        this.panels = panels;
        this.battery = battery;
    }

}
