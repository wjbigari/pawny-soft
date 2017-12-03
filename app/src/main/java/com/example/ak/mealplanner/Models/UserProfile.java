package com.example.ak.mealplanner.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.example.ak.mealplanner.Models.UserProfile.gender.FEMALE;
import static com.example.ak.mealplanner.Models.UserProfile.gender.MALE;

/**
 * Created by ak on 11/2/17.
 */

public class UserProfile implements Serializable {

    String username;
    String name;
    int age,height,weight; //age in years; height in inches; weight in pounds for calculator functions
    gender gen;
    Constraints constraints;

    public enum gender{MALE, FEMALE}

    public UserProfile(){
        this.username = "will";
        this.name = "Will";
        this.age = 1;
        this.height = 2;
        this.weight =3;
        this.gen = MALE;
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
        switch(jobject.getString("gender").toUpperCase()){
            case "MALE":
                this.gen = MALE;
                break;
            case "FEMALE":
                this.gen = FEMALE;
                break;
        }
        this.constraints = new Constraints(new JSONObject(jobject.getString("constraints")));

    }

    public String getName(){
        return name;
    }
    public String getUsername(){
        return this.username;
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
        jobject.put("gender", this.gen.name());
        jobject.put("constraints", this.constraints.toJSON().toString());
        return jobject;
    }
    
    //Verifies that the user's weight is within acceptable bounds (positive and less-or-equal to 1500 lbs)
    public boolean isValidWeight(){
    	return this.weight > 0 && this.weight <= 1500;
    }
    
    //Verifies that the user's age is within acceptable bounds (greater-or-equal to 0 and less-or-equal to 150 years)
    public boolean isValidAge(){
    	return this.age >= 0 && this.age <= 150;
    }
    
    //Verifies that the user's height is within acceptable bounds (positive and less-or-equal to 10 feet tall)
    public boolean isValidHeight(){
    	return this.height > 0 && this.height <= 120;
    }
    
    //Returns the user's current BMI based on available height and weight
    public double getBMI(){
    	if(!isValidWeight() || !isValidHeight()) return 0.0;
    	return this.weight / Math.pow(this.height, 2) * 703;
    }
    
    //Returns the user's resting metabolic rate, modified to show total energy expenditure (TEE) for sedentary activity
    public int getRMR(){
    	if(!isValidWeight() || !isValidHeight() || !isValidAge()) return 0;
    	int genderMod = (this.gen == MALE) ? 5 : -161;
    	return (int)Math.floor(((4.53 * this.weight) + (15.875 * this.height) - (4.92 * this.age) + genderMod) * 1.3);
    }
}

