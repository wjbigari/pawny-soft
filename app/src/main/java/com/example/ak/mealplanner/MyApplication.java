package com.example.ak.mealplanner;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by ak on 10/29/17.
 */

public class MyApplication extends Application {
    private ArrayList<MealItem> items;
    private ArrayList<MealItem> breakfast;
    private ArrayList<MealItem> lunch;
    private ArrayList<MealItem> dinner;
    private Constraints constraint;
    private ArrayList<String> profile = new ArrayList<String>();

    public void addBreakfast(MealItem item){
        if(breakfast == null){
            breakfast = new ArrayList<MealItem>();
        }
        breakfast.add(item);
        if(items == null){
            items = new ArrayList<MealItem>();
        }
        items.add(item);
    }

    public void addLunch(MealItem item){
        if(lunch == null){
            lunch = new ArrayList<MealItem>();
        }
        lunch.add(item);
        if(items == null){
            items = new ArrayList<MealItem>();
        }
        items.add(item);
    }

    public void addDinner(MealItem item){
        if(dinner == null){
            dinner = new ArrayList<MealItem>();
        }
        dinner.add(item);
        if(items == null){
            items = new ArrayList<MealItem>();
        }
        items.add(item);
    }

    public ArrayList<MealItem> getList(){
        if(items == null){
            items = new ArrayList<MealItem>();
            return items;
        }
        return items;
    }

    public void clearItems(){
        if(items != null){
            items.clear();
        }
        breakfast = null;
        lunch = null;
        dinner = null;
    }

    public boolean isEmpty(){
        return items == null || items.size() == 0;
    }

    public void addConstraint(Constraints constraint){
        this.constraint = constraint;
    }

    public Constraints getConstraint(){
        if(constraint != null){
            return constraint;
        }
        return new Constraints();
    }

    public boolean hasConstraint(){
        return constraint != null;
    }

    public String getName(){
        return profile.get(0);
    }

    public String getAge(){
        return profile.get(1);
    }

    public String getHeight(){
        return profile.get(2);
    }

    public String getWeight(){
        return profile.get(3);
    }

    public String getGender(){
        return profile.get(4);
    }

    public void loadProfile(ArrayList<String> profile){
        if(profile == null){
            return;
        }
        this.profile = profile;
    }

    public ArrayList<MealItem> getDinner() {
        if(dinner == null){
            dinner = new ArrayList<MealItem>();
            return dinner;
        }
        return dinner;
    }

    public ArrayList<MealItem> getLunch() {
        if(lunch == null){
            lunch = new ArrayList<MealItem>();
            return lunch;
        }
        return lunch;
    }

    public ArrayList<MealItem> getBreakfast() {
        if(breakfast == null){
            breakfast = new ArrayList<MealItem>();
            return breakfast;
        }
        return breakfast;
    }
}
