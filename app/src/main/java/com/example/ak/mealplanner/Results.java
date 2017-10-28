package com.example.ak.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import Controllers.MealPlannerController;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Constraints c = new Constraints(
                2000, 2100,			//Min Cals, Max Cals
                150, 170,			//Min Carbs, Max Carbs (in g)
                150, 170,			//Min Prot, Max Prot  (in g)
                80, 85);			//Min Fat, Max Fat  (in g)

        Intent intent = getIntent();
        ArrayList <MealItem> items = (ArrayList<MealItem>) intent.getSerializableExtra("list");
        Log.i("adarsh", items.toString());
        TextView text = (TextView) findViewById(R.id.resultview);
        MealPlannerController mpc = new MealPlannerController(c, items, text);
        mpc.execute();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                break;
        }
        return true;
    }
}
