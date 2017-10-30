package com.example.ak.mealplanner;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
    ArrayList<MealItem> items;
    FoodItem foodItem;
    MyApplication app;
    CheckBox checkBox;
    boolean locked = false;

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

        app = (MyApplication) getApplicationContext();

        TextView x = (TextView)findViewById(R.id.textView5);
        x.setText(foodItem.getName());
        x = (TextView)findViewById(R.id.textView6);
        x.setText(foodItem.toString());

        checkBox = (CheckBox) findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    locked = true;
                }
                locked = false;
            }
        });


    }

    public void addBreakFast(View view) {
        // Do something in response to button
        MainActivity main = (MainActivity)getParent();
        app.addItem(new MealItem(foodItem, locked, MealItem.Meal.BREAKFAST));
    }

    public void addLunch(View view) {
        // Do something in response to button
        MainActivity main = (MainActivity)getParent();
        app.addItem(new MealItem(foodItem, locked, MealItem.Meal.LUNCH));
    }

    public void addDinner(View view) {
        // Do something in response to button
        MainActivity main = (MainActivity)getParent();
        app.addItem(new MealItem(foodItem, locked, MealItem.Meal.DINNER));
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
