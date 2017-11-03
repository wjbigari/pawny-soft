package com.example.ak.mealplanner;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ak on 11/2/17.
 */

public class UserProfile implements Serializable {

    String username;
    String name;
    int age,height,weight;

    public JSONObject toJSON(){

    }

}
