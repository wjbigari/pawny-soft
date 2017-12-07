package com.example.ak.mealplanner.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.ak.mealplanner.Controllers.SendMealPlannerRecController;
import com.example.ak.mealplanner.Models.MealPlannerRec;
import com.example.ak.mealplanner.R;

import com.example.ak.mealplanner.Controllers.MealPlannerController;


public class ResultsActivity extends AppCompatActivity {
    MyApplication app;
    MealPlannerRec mpr;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        app = (MyApplication) getApplicationContext();
        Log.i("adarsh", app.getList().toString());
        text = (TextView) findViewById(R.id.resultview);
        MealPlannerController mpc = new MealPlannerController(app.getConstraint(), app.getList(), text,mpr, this);
        mpc.execute();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public void setMpr(MealPlannerRec mpr){
        this.mpr = mpr;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                app.clearItems();
                finish();
                break;
        }
        return true;
    }

    public void addToFavorite(View view){
        if(mpr != null) {
            SendMealPlannerRecController smprc = new SendMealPlannerRecController(app.getUser().getUsername(), mpr, null);
            smprc.execute();
            finish();
        }
        else{
            return;
        }
    }

    public void redoBuild(View view){
        if(mpr == null){
            return;
        }
        MealPlannerController mpc = new MealPlannerController(app.getConstraint(), app.getList(), text,mpr, this);
        mpc.execute();
    }
}
