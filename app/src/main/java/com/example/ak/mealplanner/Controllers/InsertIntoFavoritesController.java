package com.example.ak.mealplanner.Controllers;

import android.os.AsyncTask;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ak.mealplanner.Activities.FoodActivity;
import com.example.ak.mealplanner.Models.FoodItem;
import com.example.ak.mealplanner.Models.UserRecipe;
import com.example.ak.mealplanner.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by wbigari on 12/4/17.
 */

public class InsertIntoFavoritesController extends AsyncTask<Void,Void,Void> {
    private String dstAddress = GlobalInfo.IP;
    private int dstPort = GlobalInfo.OPTION_PORT;
    private JSONObject requestObject, responseObject;
    private String username;
    private MenuItem menuItem;
    private UserRecipe recipe = null;
    private FoodItem foodItem = null;
    private FoodActivity fa;
    Socket socket;
    public InsertIntoFavoritesController(FoodItem foodItem, String username, FoodActivity fa){
        this.username = username;
        this.foodItem = foodItem;
        this.fa = fa;
    }
    public InsertIntoFavoritesController(UserRecipe recipe, String username, FoodActivity fa){
        this.username = username;
        this.recipe = recipe;
        this.fa = fa;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            packageJSON();
            socket = new Socket(dstAddress, dstPort);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(this.requestObject.toString());
            writer.flush();

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            String responseAsString = inputStream.readLine().toString();
            responseObject = new JSONObject(responseAsString);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void packageJSON() throws JSONException {
        requestObject = new JSONObject();
        requestObject.put("option", "addToFavorites");
        requestObject.put("username", this.username);
        if(recipe != null){
            requestObject.put("isrecipe", true);
            requestObject.put("favorite", recipe.toJson().toString());
        }else{
            requestObject.put("isrecipe", false);
            requestObject.put("favorite", foodItem.toJson().toString());
        }

    }
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        try {
            fa.setFavorite(responseObject.getBoolean("success"));
        } catch (JSONException e) {
            e.printStackTrace();
        }catch(Exception e){

        }
    }
}
