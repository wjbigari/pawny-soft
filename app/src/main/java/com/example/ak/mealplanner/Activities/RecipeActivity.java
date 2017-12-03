package com.example.ak.mealplanner.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ak.mealplanner.Models.FoodItem;
import com.example.ak.mealplanner.Models.RecipeItem;
import com.example.ak.mealplanner.R;

public class RecipeActivity extends AppCompatActivity {

    FoodItem foodItem;
    MyApplication app;
    CheckBox checkBox;
    EditText servings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        foodItem = (FoodItem)intent.getSerializableExtra("foodItem");

        app = (MyApplication) getApplicationContext();

        TextView x = (TextView)findViewById(R.id.textView5);
        x.setText(foodItem.getName().toUpperCase());
        x = (TextView)findViewById(R.id.textView6);
        String details = "Serving Size: " + foodItem.getServingSize() + "\nCalories: " + foodItem.getCalPerServing() + "\nCarbohydrates: " + foodItem.getGramsCarbPerServing() + " grams" + "\nProtein: " + foodItem.getGramsProtPerServing() + " grams" + "\nFat: " + foodItem.getGramsFatPerServing() + " grams";
        x.setText(details);

        servings = (EditText) findViewById(R.id.editServing);

        Button deleteButton = (Button) findViewById(R.id.deleteRecipe);
        Button saveButton = (Button) findViewById(R.id.saveRecipeItem);

        if(app.hasUserRecipe()){
            deleteButton.setEnabled(true);
        }
        else{
            deleteButton.setEnabled(false);
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

    public void saveItem(View view) {
        if(servings.getText().toString().equals("")){
            return;
        }
        if(app.hasUserRecipe()){
            boolean updated = false;
            for(RecipeItem item : app.getUserRecipe().getIngredients()){
                if(item.getFoodItem().getFoodId() == foodItem.getFoodId()){
                    item.setNumServings(Integer.parseInt(servings.getText().toString()));
                    updated = true;
                    break;
                }
            }
            if(!updated) {
                app.getUserRecipe().addRecipeItem(new RecipeItem(foodItem, Integer.parseInt(servings.getText().toString())));
            }
        }
        else {
            app.addRecipeItem(new RecipeItem(foodItem, Integer.parseInt(servings.getText().toString())));
        }
        finish();
    }

    public void deleteRecipeItem(View view){
        RecipeItem remove = null;
        for(RecipeItem item : app.getUserRecipe().getIngredients()){
            if(item.getFoodItem().getFoodId() == foodItem.getFoodId()){
                remove = item;
                break;
            }
        }
        if(remove == null){
            return;
        }
        app.getUserRecipe().getIngredients().remove(remove);
        finish();
    }
}
