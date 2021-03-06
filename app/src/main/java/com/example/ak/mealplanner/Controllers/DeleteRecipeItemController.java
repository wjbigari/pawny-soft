package com.example.ak.mealplanner.Controllers;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by wbigari on 11/17/17.
 */

public class DeleteRecipeItemController extends AsyncTask<Void,Void,Void> {
    private int recipeItemId;
    private JSONObject requestObject;
    private JSONObject responseObject;
    private String dstAddress = GlobalInfo.IP;
    private int dstPort = GlobalInfo.OPTION_PORT;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public  DeleteRecipeItemController(int recipeItemId){
        this.recipeItemId = recipeItemId;
    }

    private void packageJson()throws JSONException{
        this.requestObject = new JSONObject();
        requestObject.put("option", "deleteRecipe");
        requestObject.put("recipeId", this.recipeItemId);

    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            packageJson();
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
}
