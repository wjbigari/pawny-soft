package com.example.ak.mealplanner.Controllers;

import android.os.AsyncTask;
import android.view.MenuItem;
import android.widget.Toast;

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
 * Created by wbigari on 12/7/17.
 */

public class DeleteFavoriteController extends AsyncTask<Void,Void,Void>{
    private String username;
    private int foodId;
    private MenuItem menuItem;
    private JSONObject requestObject,responseObject;
    private String dstAddress = GlobalInfo.IP;
    private int dstPort = GlobalInfo.OPTION_PORT;

    public DeleteFavoriteController(String username, int foodId,MenuItem menuItem){
        this.username = username;
        this.foodId = foodId;
        this.menuItem = menuItem;
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
        requestObject.put("option", "deleteFavorite");
        requestObject.put("foodid", foodId);

    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        try {
            if(responseObject.getBoolean("success")){
                menuItem.setIcon(R.drawable.favorite);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch(Exception e){

        }
    }
}
