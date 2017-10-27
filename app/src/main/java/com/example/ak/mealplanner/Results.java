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

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        TextView text = (TextView) findViewById(R.id.resultview);

//        FoodItem pineapple = new FoodItem("Pineapple", 1, 2, "oz.", 82, 21, 1, 0);
//        FoodItem bread = new FoodItem("Bread", 2, 1, "slice(s)", 60, 12, 3, 1);
//        MealItem m_pineapple = new MealItem(pineapple, MealItem.Meal.LUNCH);
//        MealItem m_bread = new MealItem(bread, MealItem.Meal.LUNCH);
//        ArrayList<MealItem> mi = new ArrayList<MealItem>();
//        mi.add(m_pineapple);
//        mi.add(m_bread);
        Intent intent = getIntent();
        ArrayList<MealItem> mealList = (ArrayList<MealItem>)intent.getSerializableExtra("list");
        Log.i("adarsh", mealList.toString());
//        Constraints c = new Constraints(
//                2000, 2100,			//Min Cals, Max Cals
//                150, 170,			//Min Carbs, Max Carbs (in g)
//                150, 170,			//Min Prot, Max Prot  (in g)
//                80, 85);
//        MealPlannerController mpc = new MealPlannerController(c,mealList,text);
//        mpc.execute();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        ArrayList <MealItem> items = (ArrayList<MealItem>) intent.getSerializableExtra("list");
//        System.out.println(items);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
