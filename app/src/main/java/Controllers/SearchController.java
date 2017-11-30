package Controllers;

/**
 * Created by wbigari on 10/26/17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;


import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ak.mealplanner.Models.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchController extends AsyncTask<Void, Void, Void> {

    String dstAddress = "10.0.2.2";
    //private String dstAddress = "174.103.187.190";
    int dstPort;
    String response = "" ;
    TextView textResponse;
    String searchString;
    ArrayList<FoodItem> foodList;
    ArrayAdapter<FoodItem> arrayAdapter;

    public SearchController(String addr,ArrayAdapter<FoodItem> mealItemArrayAdapter) {
        dstPort = 8083;
        searchString = addr;
        this.arrayAdapter = mealItemArrayAdapter;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        Socket socket = null;
        try {
            socket = new Socket(dstAddress, dstPort);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            byte[] buffer = new byte[1024];
            JSONObject requestObject = new JSONObject();
            requestObject.put("option", "search");
            requestObject.put("search", searchString);

            writer.println(requestObject.toString());
            writer.flush();

            int bytesRead;
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            String jsonresponse = "";
            jsonresponse = inputStream.readLine();
            JSONObject responseObject = new JSONObject(jsonresponse);
            JSONArray responseList = new JSONArray(responseObject.getString("search"));
            foodList = new ArrayList<FoodItem>();
            for(int i =0; i < responseList.length(); i++){
                FoodItem fi = new FoodItem(new JSONObject(responseList.getString(i)));
                foodList.add(fi);
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {


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

    }

}
