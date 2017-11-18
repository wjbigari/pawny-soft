package Controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ak.mealplanner.Models.UserProfile;
import com.example.ak.mealplanner.MyApplication;

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

public class LoginController extends AsyncTask<Void, Void,Void> {
    private JSONObject requestObject;
    private JSONObject responseObject;
    private String dstAddress = "10.0.2.2";
    private int dstPort = 8083;
    private Socket socket;
    private String username;
    private String password;
    private MyApplication app;
    public LoginController(String username, String password, MyApplication app) {
        this.username = username;
        this.password = password;
        this.app = app;
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
        this.requestObject = new JSONObject();
        this.requestObject.put("username", this.username);
        this.requestObject.put("password", this.password);
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            if(responseObject.has("userProfile")){
                UserProfile userProfile =new UserProfile( new JSONObject(responseObject.getString("userProfile")));
                app.setUserProfile(userProfile);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.onPostExecute(result);
    }

}
