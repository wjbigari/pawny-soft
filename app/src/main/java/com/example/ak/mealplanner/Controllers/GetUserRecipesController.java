package com.example.ak.mealplanner.Controllers;

import android.os.AsyncTask;

import com.example.ak.mealplanner.Models.UserRecipe;
import com.example.ak.mealplanner.Adapters.RVAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by wbigari on 11/9/17.
 */

public class GetUserRecipesController  extends AsyncTask<Void, Void, Void> {
    private RVAdapter listAdapter;
    private String username;
    private JSONObject requestObject;
    private JSONObject responseObject;
    private String dstAddress = GlobalInfo.IP;
    private int dstPort = GlobalInfo.OPTION_PORT;
    private Socket socket;

    public GetUserRecipesController(RVAdapter listAdapter, String username) {
        this.username = username;
        this.listAdapter = listAdapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Socket socket = new Socket(dstAddress, dstPort);
            PackageJSON();
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(requestObject.toString());
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

    private void PackageJSON() throws JSONException {
        requestObject = new JSONObject();
        requestObject.put("option", "getRecipes");
        requestObject.put("username", this.username);

    }
    @Override
    protected void onPostExecute(Void result) {
        if(responseObject.has("recipeList")) {
            listAdapter.clear();
            try {
                JSONArray jsonArray = new JSONArray(responseObject.getString("recipeList"));
                for (int i = 0; i < jsonArray.length(); i++) {
                    listAdapter.add(new UserRecipe(new JSONObject(jsonArray.getString(i))));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        listAdapter.notifyDataSetChanged();
        super.onPostExecute(result);
    }
}

