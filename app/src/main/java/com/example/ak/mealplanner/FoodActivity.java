package com.example.ak.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
    ArrayList<MealItem> items;
    FoodItem foodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        foodItem = (FoodItem)intent.getSerializableExtra("foodItem");
        items = (ArrayList<MealItem>) intent.getSerializableExtra("list");

        TextView x = (TextView)findViewById(R.id.textView5);
        x.setText(foodItem.getName());
        x = (TextView)findViewById(R.id.textView6);
        x.setText(foodItem.toString());
    }

    public void addBreakFast(View view) {
        // Do something in response to button
        MainActivity main = (MainActivity)getParent();
        main.addItem(new MealItem(foodItem, MealItem.Meal.BREAKFAST));
    }

    public void addLunch(View view) {
        // Do something in response to button
        MainActivity main = (MainActivity)getParent();
        main.addItem(new MealItem(foodItem, MealItem.Meal.BREAKFAST));
    }

    public void addDinner(View view) {
        // Do something in response to button
        MainActivity main = (MainActivity)getParent();
        main.addItem(new MealItem(foodItem, MealItem.Meal.BREAKFAST));
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
