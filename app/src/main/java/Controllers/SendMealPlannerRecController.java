package Controllers;

import android.os.AsyncTask;

import com.example.ak.mealplanner.MealPlannerRec;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by wbigari on 12/2/17.
 */

public class SendMealPlannerRecController extends AsyncTask<Void,Void,Void> {
    private String dstAddress = "10.0.2.2";
    //private String dstAddress = "174.103.187.190";
    private int dstPort = 8083;
    private JSONObject responseObject, requestObject;
    private MealPlannerRec mealPlannerRec;
    private Calendar cal;
    private String username;

    public SendMealPlannerRecController(String username, MealPlannerRec mpr, Calendar cal) {
        this.username = username;
        this.mealPlannerRec = mpr;
        if (cal != null) {
            this.cal = cal;
        } else {
            this.cal = Calendar.getInstance();
        }
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
        requestObject.put("username", this.username);
        requestObject.put("month", cal.get(Calendar.MONTH)+ 1);
        requestObject.put("day", cal.get(Calendar.DAY_OF_MONTH));
        requestObject.put("year", cal.get(Calendar.YEAR));
        requestObject.put("mealPlan", mealPlannerRec.toJson().toString());
        requestObject.put("option","addMeal");
    }


}