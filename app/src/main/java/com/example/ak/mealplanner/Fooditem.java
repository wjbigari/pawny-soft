package com.example.ak.mealplanner;

/**
 * Created by ak on 10/12/17.
 */

public class Fooditem {
    private String name;
    private String info;

    public Fooditem(String name, String info){
        this.name = name;
        this.info = info;
    }

    public String getName(){
        return name;
    }

    public String getInfo(){
        return info;
    }

    @Override
    public String toString(){
        return name + " " + info;
    }

}
