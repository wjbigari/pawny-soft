package Controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ak.mealplanner.UserRecipe;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wbigari on 11/11/17.
 */

public class SendUserRecipeController extends AsyncTask<Void,Void,Void> {
    private UserRecipe userRecipe;
    private JSONObject requestObject;
    private JSONObject responseObject;
    private String username;
    private String dstAddress = "10.0.2.2";
    private int dstPort = 8083;
    private Socket socket;
    Context context;

    public SendUserRecipeController(UserRecipe userRecipe, Context context, String username){
        this.userRecipe = userRecipe;
        this.context = context;
        this.username = username;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            socket = new Socket(dstAddress, dstPort);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            packageJSON();
            writer.println(this.requestObject);

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            String responseAsString = inputStream.readLine().toString();
            responseObject = new JSONObject(responseAsString);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void packageJSON() throws JSONException {
        requestObject = new JSONObject();
        requestObject.put("option", "addRecipe");
        requestObject.put("username", username);
        requestObject.put("recipe", userRecipe.toJson().toString());
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            String responseText = responseObject.getString("response");
            Toast.makeText(this.context, responseText, Toast.LENGTH_SHORT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPostExecute(result);
    }
}
