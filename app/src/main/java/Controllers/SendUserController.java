package Controllers;

import android.os.AsyncTask;

import com.example.ak.mealplanner.Constraints;
import com.example.ak.mealplanner.UserProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by wbigari on 11/3/17.
 */

public class SendUserController extends AsyncTask<Void, Void, Void> {
    private UserProfile user;
    private Constraints constraints;
    private String optionString;
    private PrintWriter out;
    private BufferedReader in;
    JSONObject responseObject;
    private Socket socket;
    String dstAddress ="10.0.2.2";
    int dstPort = 8083;

    public SendUserController(String optionString, UserProfile user, Constraints constraints){

        this.user = user;
        this.optionString = optionString;
        this.constraints = constraints;

    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Socket socket = new Socket(dstAddress, dstPort);
            JSONObject putObject = packageObject();
            out = new PrintWriter(socket.getOutputStream());
            out.println(putObject.toString());

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            responseObject = new JSONObject(response);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject packageObject() throws JSONException {
        JSONObject outObject = new JSONObject();
        outObject.put("option", this.optionString);
        outObject.put("constraints", this.constraints.toJSON().toString());
        outObject.put("userProfile", this.user.toJSON());
        return outObject;
    }
    @Override
    protected void onPostExecute(Void result) {
        try {
            String responseText = responseObject.getString("response");
            //TODO popup to display whether the operation was successful?

        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPostExecute(result);
    }
}
