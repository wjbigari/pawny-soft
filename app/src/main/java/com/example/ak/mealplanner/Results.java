package com.example.ak.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import Controllers.MealPlannerController;

public class Results extends AppCompatActivity {
    MyApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        app = (MyApplication) getApplicationContext();
        Log.i("adarsh", app.getList().toString());
        TextView text = (TextView) findViewById(R.id.resultview);
        MealPlannerController mpc = new MealPlannerController(app.getConstraint(), app.getList(), text);
        mpc.execute();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
        //TODO Will- use the controller to add the meal to faavorites
    }
}
