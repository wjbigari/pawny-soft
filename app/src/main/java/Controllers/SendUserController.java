package Controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.ak.mealplanner.Models.Constraints;
import com.example.ak.mealplanner.Models.UserProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by wbigari on 11/3/17.
 */

public class SendUserController extends AsyncTask<Void, Void, Void> {
    private UserProfile user;
    private String optionString;
    private PrintWriter out;
    private BufferedReader in;
    Context context;
    JSONObject responseObject;
    private Socket socket;
    String dstAddress ="10.0.2.2";
    int dstPort = 8083;

    public SendUserController(Context context, String optionString, UserProfile user){
        this.context = context;
        this.user = user;
        this.optionString = optionString;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Socket socket = new Socket(dstAddress, dstPort);
            JSONObject putObject = packageObject();
            out = new PrintWriter(socket.getOutputStream());
            out.println(putObject.toString());
            out.flush();

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
        outObject.put("userProfile", this.user.toJSON().toString());
        return outObject;
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
