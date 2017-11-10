package Controllers;

import android.os.AsyncTask;
import android.widget.ListAdapter;

import com.example.ak.mealplanner.UserRecipe;

/**
 * Created by wbigari on 11/9/17.
 */

public class GetUserRecipesController  extends AsyncTask<Void, Void, Void>  {
    private ListAdapter listAdapter;
    public GetUserRecipesController(ListAdapter listAdapter){

    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }
}
