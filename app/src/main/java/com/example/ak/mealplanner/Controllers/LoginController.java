package com.example.ak.mealplanner.Controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.ak.mealplanner.Activities.LoginActivity;
import com.example.ak.mealplanner.Activities.MainActivity;
import com.example.ak.mealplanner.Models.UserProfile;
import com.example.ak.mealplanner.Activities.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by wbigari on 11/18/17.
 */

public class LoginController extends AsyncTask<Void, Void,Void> {
    private JSONObject requestObject;
    private JSONObject responseObject;
    private String dstAddress = "10.0.2.2";
    //private String dstAddress = "174.103.187.190";
    private int dstPort = 8083;
    private Socket socket;
    private String username;
    private String password;
    Activity loginActivity;
    private MyApplication app;

    public LoginController(String username, String password, MyApplication app, Activity activity) {
        this.username = username;
        this.password = password;
        this.app = app;
        this.loginActivity = (LoginActivity) activity;
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

    private void packageJson() throws JSONException {
        /**
        MessageDigest md = null;
        //get a message digest
        try{
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException x){
            Log.i("adarsh", "md5 error");
        }
        //hash the password
        byte [] pass = md.digest(password.getBytes());
        */
        this.requestObject = new JSONObject();
        this.requestObject.put("option", "login");
        this.requestObject.put("username", this.username);
        this.requestObject.put("password", this.password);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        try {
            if(responseObject.has("userProfile")){
                UserProfile userProfile =new UserProfile( new JSONObject(responseObject.getString("userProfile")));
                app.addUser(userProfile);
                FileOutputStream fileOut;
                ObjectOutputStream objectOut;
                try {
                    fileOut = loginActivity.openFileOutput("AboutActivity", Context.MODE_PRIVATE);
                    objectOut = new ObjectOutputStream(fileOut);

                    objectOut.writeObject(userProfile);
                    objectOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                loginActivity.getSharedPreferences("login", Context.MODE_PRIVATE).edit().putBoolean("LOGIN", true).apply();
                Intent intent = new Intent(loginActivity, MainActivity.class);
                loginActivity.startActivity(intent);
                loginActivity.finish();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
