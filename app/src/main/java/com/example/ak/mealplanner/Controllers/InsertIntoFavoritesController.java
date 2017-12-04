package com.example.ak.mealplanner.Controllers;

import android.os.AsyncTask;

import com.example.ak.mealplanner.Models.FoodItem;
import com.example.ak.mealplanner.Models.UserRecipe;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wbigari on 12/4/17.
 */

public class InsertIntoFavoritesController extends AsyncTask<Void,Void,Void> {
    private String dstAddress = "10.0.2.2";
    //private String dstAddress = "174.103.187.190";
    private int dstPort = 8083;
    private JSONObject requestObject, responseObject;
    private String username;
    private UserRecipe recipe = null;
    private FoodItem foodItem = null;
    public InsertIntoFavoritesController(FoodItem foodItem, String username){
        this.username = username;
        this.foodItem = foodItem;
    }
    public InsertIntoFavoritesController(UserRecipe recipe, String username){
        this.username = username;
        this.recipe = recipe;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            packageJSON();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void packageJSON() throws JSONException {
        requestObject = new JSONObject();
        requestObject.put("option", "addToFavorites");
        requestObject.put("username", this.username);

    }
}
