package com.eyedentify.vinitapenmatsa.atum.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by vinitapenmatsa on 1/3/18.
 */

@IgnoreExtraProperties
public class User {

    public String userId;
    public String name;
    public String email;
    public String company;
    public List<Unit> units;


    public User(){

    }

    public User(String userId,String name, String email , String company, List<Unit> units ){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.company = company;
        this.units = units;
    }

}
