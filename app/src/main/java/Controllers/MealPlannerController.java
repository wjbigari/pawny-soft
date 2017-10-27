package Controllers;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.ak.mealplanner.Constraints;
import com.example.ak.mealplanner.FoodItem;
import com.example.ak.mealplanner.MealItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


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

    public MealPlannerController(Constraints c, ArrayList<MealItem> rl, TextView response){
        this.requestList = rl;
        this.requestConstraints = c;
        this.textResponse = response;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String requestString = MakeRequestString();
        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);
            OutputStream byteArrayOutputStream = socket.getOutputStream();
            Log.i("al", "I'm here");
            byte[] buffer = new byte[1024];
            byteArrayOutputStream.write(requestString.getBytes());
            byteArrayOutputStream.flush();
            Log.i("al", "I'm here1");
            int bytesRead;
            Log.i("al", "I'm here2");
            InputStream inputStream = socket.getInputStream();
            Log.i("al", "I'm here3");
			/*
			 * notice: inputStream.read() will block if no data return
			 */
            String jsonresponse = "";
            Log.i("al", "I'm here4");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Log.i("al", "I'm here 2.5");
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                Log.i("al", "I'm here5");
                outputStream.write(buffer, 0, bytesRead);
                jsonresponse += byteArrayOutputStream.toString();
            }

            JSONArray mealItemsJSON = new JSONArray(jsonresponse);
            for(int i=0; i < mealItemsJSON.length(); i++){
                responseList.add(new MealItem(mealItemsJSON.getJSONObject(i)));
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
            for (int i = 0; i < requestList.size(); i++) {
                requestJSONArray.put(requestList.get(i).toJSON().toString());
            }
            String constraintsJSONString = requestConstraints.toJSON().toString();
            sendObject.put("constraints", constraintsJSONString);
            sendObject.put("mealList", requestJSONArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendObject.toString();
    }
    @Override
    protected void onPostExecute(Void result) {
        String response = "";
        for(int i =0; i < responseList.size(); i++){
            response += responseList.get(i).getFoodItem().getName() + "\n";
        }
        textResponse.setText(response);
        super.onPostExecute(result);
    }
}
