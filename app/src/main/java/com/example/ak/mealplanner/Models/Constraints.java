package com.example.ak.mealplanner.Models;

/**
 * Created by wbigari on 10/26/17.
 */

import org.json.JSONException;

/**
 * Created by wbigari on 10/26/17.
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Constraints implements Serializable{
    //Fields for constraints set by the user; all nutrient fields are to be stored as Calories, not as Grams
    private int minCals;
    private int maxCals;
    private int minCarbs;
    private int maxCarbs;
    private int minProt;
    private int maxProt;
    private int minFat;
    private int maxFat;

    //This constructor sets all fields of the constraints according to the values passed in
    public Constraints(int calMin, int calMax, int carbMin, int carbMax, int protMin, int protMax, int fatMin, int fatMax) {
        minCals = calMin;
        maxCals = calMax;
        minCarbs = carbMin;
        maxCarbs = carbMax;
        minProt = protMin;
        maxProt = protMax;
        minFat = fatMin;
        maxFat = fatMax;
    }

    //No-arg constructor sets all fields to an invalid state (-1); when using this constructor any fields used must be set via Setter methods
    public Constraints() {
        minCals = 2000;
        maxCals = 2100;
        minCarbs = 150;
        maxCarbs = 170;
        minProt = 150;
        maxProt = 170;
        minFat = 80;
        maxFat = 85;
    }

    public Constraints(JSONObject jc) throws JSONException {
        minCals = jc.getInt("minCals");
        maxCals = jc.getInt("maxCals");
        minCarbs = jc.getInt("minCarbs");
        maxCarbs = jc.getInt("maxCals");
        minProt = jc.getInt("minProt");
        maxProt = jc.getInt("maxProt");
        minFat = jc.getInt("minFat");
        maxFat = jc.getInt("maxFat");
    }

    //Getters
    public int getMinCals() {
        return minCals;
    }

    public int getMaxCals() {
        return maxCals;
    }

    public int getMinCarbs() {
        return minCarbs;
    }

    public int getMaxCarbs() {
        return maxCarbs;
    }

    public int getMinProt() {
        return minProt;
    }

    public int getMaxProt() {
        return maxProt;
    }

    public int getMinFat() {
        return minFat;
    }

    public int getMaxFat() {
        return maxFat;
    }

    //Setters
    public void setMinCals(int minCals) {
        this.minCals = minCals;
    }

    public void setMaxCals(int maxCals) {
        this.maxCals = maxCals;
    }

    public void setMinCarbs(int minCarbs) {
        this.minCarbs = minCarbs;
    }

    public void setMaxCarbs(int maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public void setMinProt(int minProt) {
        this.minProt = minProt;
    }

    public void setMaxProt(int maxProt) {
        this.maxProt = maxProt;
    }

    public void setMinFat(int minFat) {
        this.minFat = minFat;
    }

    public void setMaxFat(int maxFat) {
        this.maxFat = maxFat;
    }

    //TODO: Set up JSON serialization/deserialization functions
    public JSONObject toJSON() throws JSONException {
        JSONObject rj = new JSONObject();
        rj.put("minCals", minCals);
        rj.put("maxCals", maxCals);
        rj.put("minCarbs", minCarbs);
        rj.put("maxCarbs", maxCarbs);
        rj.put("minProt", minProt);
        rj.put("maxProt", maxProt);
        rj.put("minFat", minFat);
        rj.put("maxFat", maxFat);
        return rj;
    }
}

