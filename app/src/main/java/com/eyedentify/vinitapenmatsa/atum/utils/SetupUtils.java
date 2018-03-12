package com.eyedentify.vinitapenmatsa.atum.utils;

import android.util.Log;

import com.eyedentify.vinitapenmatsa.atum.models.Battery;
import com.eyedentify.vinitapenmatsa.atum.models.Panel;
import com.eyedentify.vinitapenmatsa.atum.models.Unit;
import com.eyedentify.vinitapenmatsa.atum.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinitapenmatsa on 1/3/18.
 */

public class SetupUtils {

    private static final DatabaseReference mDatatbase = FirebaseDatabase.getInstance().getReference();



    public static void setupData(){

        // set up panels test data
        List<Panel> panels = new ArrayList<Panel>();
        panels.add(new Panel(42.1,336.8,"NORMAL"));
        panels.add(new Panel(40.2,321.6,"NORMAL"));
        panels.add(new Panel(39.7,317.6,"NORMAL"));
        panels.add(new Panel(43.6,348.8,"NORMAL"));
        panels.add(new Panel(44.2,353.6,"NORMAL"));
        panels.add(new Panel(41.1,328.8,"NORMAL"));
        panels.add(new Panel(34.3,274.4,"NORMAL"));
        panels.add(new Panel(19,152,"VOLTAGE_TOO_HIGH"));
        panels.add(new Panel(39.2,313.6,"NORMAL"));
        panels.add(new Panel(52.8,422.4,"VOLTAGE_TOO_HIGH"));

        // Set up battery modules test data.

        List<Battery> batteryModules = new ArrayList<Battery>();
        batteryModules.add(new Battery(14.8,74.0,"VOLTAGE_TOO_HIGH"));
        batteryModules.add(new Battery(13,65,"NORMAL"));
        batteryModules.add(new Battery(13.5,67.5,"NORMAL"));
        batteryModules.add(new Battery(9,45,"VOLTAGE_TOO_LOW"));
        batteryModules.add(new Battery(13.7,68.5,"NORMAL"));

        Unit unit = new Unit(panels,batteryModules);

        List<Unit> units = new ArrayList<>();
        units.add(unit);

        writeNewUser("eyedentify", "eyedentify" , "admin@eyedentify.com" , "eyedentify" , units);


    }


    public static void writeNewUser(String userId, String name , String email , String company , List<Unit> units){
        User user = new User(userId,name,email,company,units);
        mDatatbase.child("users").child(userId).setValue(user);
        Log.i("SETUP" , "setup successful");
    }



}
