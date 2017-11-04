package com.example.ak.mealplanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ak on 11/2/17.
 */

public class UserProfile implements Serializable {

    String username;
    String name;
    int age,height,weight;
    public UserProfile(JSONObject jobject) throws JSONException {
        this.username = jobject.getString("username");
        this.name = jobject.getString("name");
        this.age = jobject.getInt("age");
        this.height = jobject.getInt("height");
        this.weight = jobject.getInt("weight");

    }

    public String getHeighString(){
        int feet = this.height/12;
        int inches = this.height % 12;
        return "" + feet + "' " + inches + " \"";
    }
    public JSONObject toJSON() throws JSONException {
        JSONObject jobject = new JSONObject();
        jobject.put("username", this.username);
        jobject.put("name", this.name);
        jobject.put("age", this.age);
        jobject.put("height", this.height);
        jobject.put("weight", this.weight);
        return jobject;
    }
}
