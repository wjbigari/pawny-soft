package com.example.ak.mealplanner;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by ak on 10/29/17.
 */

public class MyApplication extends Application {
    private ArrayList<MealItem> items;
    private Constraints constraint;
    private ArrayList<String> profile = new ArrayList<String>();

    public void addItem(MealItem item){
        if(items == null){
            items = new ArrayList<MealItem>();
        }
        items.add(item);
    }

    public ArrayList<MealItem> getList(){
        if(items != null){
            return items;
        }
        return new ArrayList<MealItem>();
    }

    public void clearItems(){
        items.clear();
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
}
