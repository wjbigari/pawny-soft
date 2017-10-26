package Controllers;

/**
 * Created by wbigari on 10/26/17.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ak.mealplanner.FoodItem;
import com.example.ak.mealplanner.MealItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchController extends AsyncTask<Void, Void, Void> {

    String dstAddress = "localhost";
    int dstPort;
    String response = "" ;
    TextView textResponse;
    String searchString;
    ArrayList<FoodItem> foodList;
    ArrayAdapter<FoodItem> arrayAdapter;

    SearchController(String addr,ArrayAdapter<FoodItem> mealItemArrayAdapter) {
        dstAddress ="10.0.2.2";
        dstPort = 8083;
        searchString = addr + "\r\n";
        this.arrayAdapter = mealItemArrayAdapter;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);
            OutputStream byteArrayOutputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            byte[] stringb = (searchString + "\r\n").getBytes();
            byteArrayOutputStream.write(stringb, 0, stringb.length);
            byteArrayOutputStream.flush();


            int bytesRead;
            InputStream inputStream = socket.getInputStream();

			/*
			 * notice: inputStream.read() will block if no data return
			 */
            String jsonresponse = "";
            byteArrayOutputStream = new ByteArrayOutputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                jsonresponse += byteArrayOutputStream.toString();
            }

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
        arrayAdapter.addAll(foodList);
        super.onPostExecute(result);
    }

}
