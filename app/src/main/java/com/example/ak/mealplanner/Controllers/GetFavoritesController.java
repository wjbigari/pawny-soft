package com.example.ak.mealplanner.Controllers;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.ak.mealplanner.Models.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by wbigari on 12/7/17.
 */

public class GetFavoritesController extends AsyncTask <Void,Void,Void> {
    private String dstAddress = GlobalInfo.IP;
    private int dstPort = GlobalInfo.OPTION_PORT;
    private JSONObject requestObject, responseObject;
    private String username;
    private ArrayAdapter<FoodItem> arrayAdapter;
    public GetFavoritesController(String username, ArrayAdapter arrayAdapter){
        this.username = username;
        this.arrayAdapter = arrayAdapter;
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
        requestObject.put("option", "getFavorites");
        requestObject.put("username", username);
    }
    @Override
    protected void onPostExecute(Void result) {

        try {
            JSONArray responseList = new JSONArray(responseObject.getString("foodlist"));
            ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();
            for(int i =0; i < responseList.length(); i++){
                FoodItem fi = new FoodItem(new JSONObject(responseList.getString(i)));
                foodList.add(fi);
            }
            if(foodList != null && foodList.size() > 0) {
                arrayAdapter.clear();
                for(FoodItem fi : foodList){
                    arrayAdapter.add(fi);
                }
                arrayAdapter.sort(new Comparator<FoodItem>() {
                    @Override
                    public int compare(FoodItem o1, FoodItem o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                arrayAdapter.notifyDataSetChanged();
                super.onPostExecute(result);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
