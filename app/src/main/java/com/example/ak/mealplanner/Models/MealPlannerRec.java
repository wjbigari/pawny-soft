package com.example.ak.mealplanner.Models;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

import com.example.ak.mealplanner.Models.MealItem;

public class MealPlannerRec implements Serializable{
    private ArrayList<MealItem> breakfastItems;
    private ArrayList<MealItem> lunchItems;
    private ArrayList<MealItem> dinnerItems;

    private int totalCals;
    private int totalCarbs;
    private int totalProt;
    private int totalFat;

    //Empty constructor creates a blank recommendation with no statistics
    public MealPlannerRec(){
        breakfastItems = new ArrayList<>();
        lunchItems = new ArrayList<>();
        dinnerItems = new ArrayList<>();
        totalCals = 0;
        totalCarbs = 0;
        totalProt = 0;
        totalFat = 0;
    }
    //Constructor with a provided list of items will build a recommendation out of that list
    public MealPlannerRec(ArrayList<MealItem> allItems){
        this();
        for(MealItem item : allItems){
            this.addItemToRec(item);
        }
    }
    //Constructor with provided JSON will convert that JSON into a MealPlannerRec instance
    public MealPlannerRec(JSONObject recJson) throws JSONException{
        this();
        this.fromJson(recJson);
    }

    //Getters
    public int getTotalCals(){return this.totalCals;}
    public int getTotalCarbs(){return this.totalCarbs;}
    public int getTotalProt(){return this.totalProt;}
    public int getTotalFat(){return this.totalFat;}
    public ArrayList<MealItem> getBreakfastItems(){return this.breakfastItems;}
    public ArrayList<MealItem> getLunchItems(){return this.lunchItems;}
    public ArrayList<MealItem> getDinnerItems(){return this.dinnerItems;}

    //Helper method for adding a MealItem to this MealPlanner recommendation
    public void addItemToRec(MealItem item){
        if(item.getNumServings() > 0){
            switch(item.getMeal()){
                case BREAKFAST:
                    breakfastItems.add(new MealItem(item.getFoodItem(), item.isLocked(), item.getNumServings(), MealItem.Meal.BREAKFAST));
                    break;
                case LUNCH:
                    lunchItems.add(new MealItem(item.getFoodItem(), item.isLocked(), item.getNumServings(), MealItem.Meal.LUNCH));
                    break;
                case DINNER:
                    dinnerItems.add(new MealItem(item.getFoodItem(), item.isLocked(), item.getNumServings(), MealItem.Meal.DINNER));
                    break;
            }
            totalCals += (item.getNumServings() * item.getFoodItem().getCalPerServing());
            totalCarbs += (item.getNumServings() * item.getFoodItem().getGramsCarbPerServing());
            totalProt += (item.getNumServings() * item.getFoodItem().getGramsProtPerServing());
            totalFat += (item.getNumServings() * item.getFoodItem().getGramsFatPerServing());
        }
    }


    //JSON serialization and de-serialization functions
    public JSONObject toJson() throws JSONException{
        JSONObject out = new JSONObject();
        for(MealItem item : this.breakfastItems){
            out.accumulate("breakfastItems", item.toJson());
        }
        for(MealItem item : this.lunchItems){
            out.accumulate("lunchItems", item.toJson());
        }
        for(MealItem item : this.dinnerItems){
            out.accumulate("dinnerItems", item.toJson());
        }
        out.put("totalCals", this.totalCals);
        out.put("totalCarbs", this.totalCarbs);
        out.put("totalProt", this.totalProt);
        out.put("totalFat", this.totalFat);
        return out;
    }

    public void fromJson(JSONObject in) throws JSONException{
        this.breakfastItems.clear();
        this.lunchItems.clear();
        this.dinnerItems.clear();
        JSONArray items = new JSONArray(in.getString("breakfastItems"));
        if(items != null && items.length() != 0){
            for(int i = 0; i < items.length(); i++){
                breakfastItems.add(new MealItem(new JSONObject(items.getString(i))));
            }
        }
        items = new JSONArray(in.getString("lunchItems"));
        if(items != null){
            for(int i = 0; i < items.length(); i++){
                lunchItems.add(new MealItem(new JSONObject(items.getString(i))));
            }
        }
        items = new JSONArray(in.getString("dinnerItems"));
        if(items != null){
            for(int i = 0; i < items.length(); i++){
                dinnerItems.add(new MealItem(new JSONObject(items.getString(i))));
            }
        }
        this.totalCals = in.getInt("totalCals");
        this.totalCarbs = in.getInt("totalCarbs");
        this.totalProt = in.getInt("totalProt");
        this.totalFat = in.getInt("totalFat");
    }

    //toString function to help with visualization of the structure of a meal recommendation
    @Override
    public String toString(){
        String result = "";
        result += "Total Calories - " + this.totalCals + "\n";
        result += "Total Carbs - " + this.totalCarbs + "\n";
        result += "Total Protein - " + this.totalProt + "\n";
        result += "Total Fat - " + this.totalFat + "\n";
        return result;
    }

    public String contstraintsString(){
        String result = "";
        result += "Total Calories - " + this.totalCals + "\n";
        result += "Total Carbs - " + this.totalCarbs + "\n";
        result += "Total Protein - " + this.totalProt + "\n";
        result += "Total Fat - " + this.totalFat + "\n";
        return result;
    }
}
