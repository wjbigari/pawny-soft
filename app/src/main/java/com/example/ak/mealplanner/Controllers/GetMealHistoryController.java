package com.example.ak.mealplanner.Controllers;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.ak.mealplanner.Activities.HistoryFragment;
import com.example.ak.mealplanner.Models.MealPlannerRec;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wbigari on 12/2/17.
 */

public class GetMealHistoryController extends AsyncTask<Void,Void,Void> {
    private String dstAddress = "10.0.2.2";
    //private String dstAddress = "174.103.187.190";
    private int dstPort = 8083;
    private JSONObject responseObject, requestObject;
    private Calendar cal;
    private String username;
    private ArrayAdapter listAdapter;
    private CompactCalendarView calendarView = null;
    private HistoryFragment thiscontext;
    public GetMealHistoryController(String username, Calendar cal, ArrayAdapter listAdapter){
        this.username = username;
        if(cal!=null){
            this.cal = cal;
        }else{
            cal = Calendar.getInstance();
        }
        this.listAdapter = listAdapter;
    }
    public GetMealHistoryController(String username, CompactCalendarView cv, HistoryFragment thisContext){
        this.username = username;
        this.calendarView = cv;
        Date date = calendarView.getFirstDayOfCurrentMonth();
        this.cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        this.thiscontext = thisContext;
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
        requestObject.put("username", username);
        requestObject.put("month", cal.get(Calendar.MONTH)+1);
        requestObject.put("year", cal.get(Calendar.YEAR));
        requestObject.put("option","getMealHistory");
    }
    @Override
    protected void onPostExecute(Void result) {
        boolean calendar = false;
        if(calendarView!=null){
            calendar = true;
        }
        try {
            MealPlannerRec mpr = new MealPlannerRec();
            MealPlannerRec mptemp;
            JSONObject mealListObject = new JSONObject(responseObject.getString("mealList"));
            int daysInMonth= cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for(int i = 1; i<= daysInMonth; i++){
                if(mealListObject.has(""+ i)){
                    mptemp = new MealPlannerRec(new JSONObject(mealListObject.getString(""+i)));
                    if(calendar){
                        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), i);
                        Log.i("adarsh", cal.get(Calendar.YEAR)+ " :"+ cal.get(Calendar.MONTH) +":"+i);
                        Event event = new Event(Color.RED,cal.getTimeInMillis(),mptemp);
                        thiscontext.addEvent(event);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
