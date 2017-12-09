package com.example.ak.mealplanner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ak.mealplanner.Adapters.CustomAdapter;
import com.example.ak.mealplanner.Models.FoodItem;
import com.example.ak.mealplanner.Models.MealItem;
import com.example.ak.mealplanner.Models.MealPlannerRec;
import com.example.ak.mealplanner.R;

/**
 * Created by wbigari on 12/7/17.
 */

public class MealHistoryActivity extends AppCompatActivity {
    private CustomAdapter mAdapter;
    private  MyApplication app;
    private MealPlannerRec mpr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        mpr = (MealPlannerRec) intent.getSerializableExtra("mealplan");

        mAdapter = new CustomAdapter(this);

        ListView list = (ListView) findViewById(R.id.listHistoryRec);


        FoodItem head = new FoodItem();
        head.setName("Breakfast");

        mAdapter.addSectionHeaderItem(new MealItem(head, MealItem.Meal.BREAKFAST));

        for(MealItem x : mpr.getBreakfastItems()){
            mAdapter.addItem(x);
        }

        head = new FoodItem();
        head.setName("Lunch");

        mAdapter.addSectionHeaderItem(new MealItem(head, MealItem.Meal.BREAKFAST));

        for(MealItem x : mpr.getLunchItems()){
            mAdapter.addItem(x);
        }

        head = new FoodItem();
        head.setName("Dinner");

        mAdapter.addSectionHeaderItem(new MealItem(head, MealItem.Meal.BREAKFAST));

        for(MealItem x : mpr.getDinnerItems()){
            mAdapter.addItem(x);
        }

        mAdapter.notifyDataSetChanged();

        list.setAdapter(mAdapter);

        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                MealItem meal = (MealItem) parent.getItemAtPosition(position);
                FoodItem item = (FoodItem) meal.getFoodItem();

                Intent intent = new Intent(MealHistoryActivity.this, FoodActivity.class);
                intent.putExtra("foodItem", item);
                startActivity(intent);
            }
        });

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
