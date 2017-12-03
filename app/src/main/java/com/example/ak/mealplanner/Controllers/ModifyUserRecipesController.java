package com.example.ak.mealplanner.Controllers;

import android.os.AsyncTask;

import com.example.ak.mealplanner.Models.UserRecipe;

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

public class ModifyUserRecipesController extends AsyncTask<Void, Void,Void> {
    private JSONObject requestObject;
    private JSONObject responseObject;
    private String dstAddress = "10.0.2.2";
    //private String dstAddress = "174.103.187.190";
    private int dstPort = 8083;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private UserRecipe userRecipe;
    private String username;
    public ModifyUserRecipesController(UserRecipe userRecipe, String username ){
        this.userRecipe = userRecipe;
        this.username = username;
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

    public void packageJson() throws JSONException{
        requestObject = new JSONObject();
        requestObject.put("option", "modifyRecipe");
        requestObject.put("username", this.username);
        requestObject.put("userRecipe", userRecipe.toJson().toString());
    }
}
