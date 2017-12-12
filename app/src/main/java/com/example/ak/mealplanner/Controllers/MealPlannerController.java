package com.example.ak.mealplanner.Controllers;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.ak.mealplanner.Activities.ResultsActivity;
import com.example.ak.mealplanner.Adapters.CustomAdapter;
import com.example.ak.mealplanner.Models.Constraints;
import com.example.ak.mealplanner.Models.FoodItem;
import com.example.ak.mealplanner.Models.MealItem;
import com.example.ak.mealplanner.Models.MealPlannerRec;

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

/**
 * Created by wbigari on 10/26/17.
 */

public class MealPlannerController extends AsyncTask<Void, Void, Void> {
    private String dstAddress = GlobalInfo.IP;
    private int dstPort = GlobalInfo.MEALPLANNER_PORT;
    private ArrayList<MealItem> requestList;
    private Constraints requestConstraints;
    private ArrayList<MealItem> responseList;
    private CustomAdapter customAdapter;
    private MealPlannerRec mealPlannerRec;
    private ResultsActivity results;
    private TextView text;


    public MealPlannerController(Constraints c, ArrayList<MealItem> rl, CustomAdapter adapter, MealPlannerRec rec, TextView text, ResultsActivity results){
        this.results = results;
        this.requestList = rl;
        this.requestConstraints = c;
        this.customAdapter = adapter;
        this.text = text;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String requestString = MakeRequestString();
        Socket socket = null;
        BufferedReader in;
        try {
            socket = new Socket(dstAddress, dstPort);
            PrintWriter writer  = new PrintWriter(socket.getOutputStream());

            writer.println(requestString);
            Log.i("al", requestString);
            writer.flush();
            socket.setSoTimeout(10*1000);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            String lineInput;
            String jsonresponse = null;
            while((lineInput = in.readLine()) != null){
                jsonresponse = lineInput;
            }
            JSONObject mealRecJSON = new JSONObject(jsonresponse);
            Log.i("al",mealRecJSON.toString(2));
            mealPlannerRec = new MealPlannerRec(mealRecJSON);
            results.setMpr(mealPlannerRec);
            Log.i("al", "HEY THIS IS THE MEAL PLAN " + mealPlannerRec.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("al", e.getMessage() + e.getClass().toString());
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String MakeRequestString(){
        JSONObject sendObject = new JSONObject();
        try {
            JSONArray requestJSONArray = new JSONArray();
            for(MealItem mi : requestList) {
                Log.i("al", mi.toString());
                requestJSONArray.put(mi.toJson().toString());
            }
            String constraintsJSONString = requestConstraints.toJSON().toString();
            Log.i("al", constraintsJSONString);
            sendObject.put("constraints", constraintsJSONString);
            sendObject.put("mealList", requestJSONArray.toString());
            Log.i("al", requestJSONArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendObject.toString();
    }
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        FoodItem head = new FoodItem();
        head.setName("Breakfast");

        customAdapter.addSectionHeaderItem(new MealItem(head, MealItem.Meal.BREAKFAST));

        for(MealItem x : mealPlannerRec.getBreakfastItems()){
            customAdapter.addItem(x);
        }

        head = new FoodItem();
        head.setName("Lunch");

        customAdapter.addSectionHeaderItem(new MealItem(head, MealItem.Meal.BREAKFAST));

        for(MealItem x : mealPlannerRec.getLunchItems()){
            customAdapter.addItem(x);
        }

        head = new FoodItem();
        head.setName("Dinner");

        customAdapter.addSectionHeaderItem(new MealItem(head, MealItem.Meal.BREAKFAST));

        for(MealItem x : mealPlannerRec.getDinnerItems()){
            customAdapter.addItem(x);
        }

        customAdapter.notifyDataSetChanged();

        text.setText(mealPlannerRec.toString());
    }
}
