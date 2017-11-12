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
    private ArrayList<RecipeItem> recipes;
    UserProfile user;

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
        user.setConstraints(constraint);
    }

    public Constraints getConstraint(){
        if(user != null) {
            return user.getConstraints();
        }
        this.user = new UserProfile();
        return user.getConstraints();
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

    public void addUser(UserProfile user){
        if(user != null){
            this.user = user;
        }
        else{
            this.user = new UserProfile();
        }
    }

    public UserProfile getUser(){
        if(user != null){
            return user;
        }
        return new UserProfile();
    }

    public void addRecipeItem(RecipeItem item){
        if(recipes == null){
            recipes = new ArrayList<RecipeItem>();
        }
        recipes.add(item);
    }

    public void clearRecipeItems(){
        if(recipes != null){
            recipes.clear();
        }
    }

    public void hasRecipeItem(RecipeItem item){
        if(recipes != null){

        }
    }

    public boolean hasIngredients(){
        if(recipes != null){
            if(recipes.size() == 0){
                return false;
            }
        }
        return true;
    }

    public ArrayList<RecipeItem> getIngredients(){
        if(recipes != null){
            return recipes;
        }
        return new ArrayList<RecipeItem>();
    }
}
