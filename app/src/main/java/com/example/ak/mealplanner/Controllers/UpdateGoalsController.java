package com.example.ak.mealplanner.Controllers;

import android.content.Context;
import android.os.AsyncTask;

import com.example.ak.mealplanner.Models.Constraints;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by wbigari on 11/18/17.
 */

public class UpdateGoalsController extends AsyncTask<Void, Void,Void> {
    private JSONObject requestObject;
    private JSONObject responseObject;
    private String dstAddress = GlobalInfo.IP;
    private int dstPort = GlobalInfo.OPTION_PORT;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Constraints constraints;
    private Context context;
    private String username;


    public UpdateGoalsController(Constraints constraints, Context context, String username) {
        this.constraints = constraints;
        this.context = context;
        this.username = username;

    }

    private void packageJson() throws JSONException {
        requestObject = new JSONObject();
        requestObject.put("option", "updateGoals");
        requestObject.put("constraints", this.constraints.toJSON().toString());
        requestObject.put("username", this.username);
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
