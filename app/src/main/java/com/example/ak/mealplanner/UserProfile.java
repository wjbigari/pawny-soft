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
    gender gen;
    Constraints constraints;

    public enum gender{MALE, FEMALE}

    public UserProfile(){
        this.username = "will";
        this.name = "Will";
        this.age = 1;
        this.height = 2;
        this.weight =3;
        this.gen = gender.MALE;
        this.constraints = new Constraints();
    }

    public UserProfile(String username, String name, int age, int height, int weight, gender gen){
        this.username = username;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gen = gen;
        this.constraints = new Constraints();
    }

    public UserProfile(JSONObject jobject) throws JSONException {
        this.username = jobject.getString("username");
        this.name = jobject.getString("name");
        this.age = jobject.getInt("age");
        this.height = jobject.getInt("height");
        this.weight = jobject.getInt("weight");

    }

    public String getName(){
        return name;
    }

    public String getAge(){
        return Integer.toString(age);
    }

    public String getWeight(){
        return Integer.toString(weight);
    }

    public String getHeight(){
        return Integer.toString(height);
    }

    public gender getGen(){
        return gen;
    }

    public Constraints getConstraints() {
        if(constraints != null) {
            return constraints;
        }
        return new Constraints();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public void setGen(gender gen){
        this.gen = gen;
    }

    public void setConstraints(Constraints constraints){
        this.constraints = constraints;
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
