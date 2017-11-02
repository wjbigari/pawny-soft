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
    EditText servings;

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
        x.setText(foodItem.getName().toUpperCase());
        x = (TextView)findViewById(R.id.textView6);
        String details = "Serving Size: " + foodItem.getServingSize() + "\nCalories: " + foodItem.getCalPerServing() + "\nCarbohydrates: " + foodItem.getGramsCarbPerServing() + " grams" + "\nProtein: " + foodItem.getGramsProtPerServing() + " grams" + "\nFat: " + foodItem.getGramsFatPerServing() + " grams";
        x.setText(details);

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

        servings = (EditText) findViewById(R.id.editServing);


    }

    public void addBreakFast(View view) {
        // Do something in response to button
        if(servings.getText().toString().equals("")){
            app.addBreakfast(new MealItem(foodItem, locked, MealItem.Meal.BREAKFAST));
        }
        else{
            app.addBreakfast(new MealItem(foodItem, locked, Integer.parseInt(servings.getText().toString()), MealItem.Meal.BREAKFAST));
        }

    }

    public void addLunch(View view) {
        // Do something in response to button
        if(servings.getText().toString().equals("")){
            app.addLunch(new MealItem(foodItem, locked, MealItem.Meal.LUNCH));
        }
        else{
            app.addLunch(new MealItem(foodItem, locked, Integer.parseInt(servings.getText().toString()), MealItem.Meal.LUNCH));
        }
    }

    public void addDinner(View view) {
        // Do something in response to button
        if(servings.getText().toString().equals("")){
            app.addDinner(new MealItem(foodItem, locked, MealItem.Meal.DINNER));
        }
        else{
            app.addDinner(new MealItem(foodItem, locked, Integer.parseInt(servings.getText().toString()), MealItem.Meal.DINNER));
        }
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
