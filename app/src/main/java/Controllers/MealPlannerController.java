package Controllers;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.ak.mealplanner.Constraints;
import com.example.ak.mealplanner.FoodItem;
import com.example.ak.mealplanner.MealItem;
import com.example.ak.mealplanner.MealPlannerRec;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.io.PrintWriter;
import java.net.Socket;

import java.net.UnknownHostException;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Created by wbigari on 10/26/17.
 */

public class MealPlannerController extends AsyncTask<Void, Void, Void> {
    private String dstAddress = "10.0.2.2" ;
    private int dstPort = 8080;
    private ArrayList<MealItem> requestList;
    private Constraints requestConstraints;
    private ArrayList<MealItem> responseList;
    private TextView textResponse;
    MealPlannerRec mealPlannerRec;

    public MealPlannerController(Constraints c, ArrayList<MealItem> rl, TextView response){
        this.requestList = rl;
        this.requestConstraints = c;
        this.textResponse = response;
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
        textResponse.setText(mealPlannerRec.toString());
        super.onPostExecute(result);
    }
}
