package com.example.ak.mealplanner.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.ak.mealplanner.Models.RecipeItem;
import com.example.ak.mealplanner.Models.UserRecipe;
import com.example.ak.mealplanner.R;

import java.util.ArrayList;

import com.example.ak.mealplanner.Controllers.ModifyUserRecipesController;
import com.example.ak.mealplanner.Controllers.SendUserRecipeController;

public class RecipeSaveActivity extends AppCompatActivity {
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_save);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        app = (MyApplication) getApplication();

        if(app.hasUserRecipe()){
            EditText x = (EditText) findViewById(R.id.foodNameEdit);
            x.setText(app.getUserRecipe().getName());

            x = (EditText) findViewById(R.id.portionEdit);
            x.setText(Integer.toString(app.getUserRecipe().getNumPortions()));

            x = (EditText) findViewById(R.id.portionNameEdit);
            x.setText(app.getUserRecipe().getServingUnit());

            x = (EditText) findViewById(R.id.instructEdit);
            x.setText(app.getUserRecipe().getPrepInstructions());
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

    public void addRecipe(View view) {
        EditText x = (EditText) findViewById(R.id.foodNameEdit);
        if(x.getText().toString().equals("")){
            return;
        }
        String foodName = x.getText().toString();
        x = (EditText) findViewById(R.id.portionEdit);
        if(x.getText().toString().equals("")){
            return;
        }
        int portion = Integer.parseInt(x.getText().toString());
        x = (EditText) findViewById(R.id.portionNameEdit);
        if(x.getText().toString().equals("")){
            return;
        }
        String portionName = x.getText().toString();
        x = (EditText) findViewById(R.id.instructEdit);
        if(x.getText().toString().equals("")){
            return;
        }
        String instruct = x.getText().toString();

        if(app.hasUserRecipe()){
            app.getUserRecipe().setName(foodName);
            app.getUserRecipe().setNumPortions(portion);
            app.getUserRecipe().setServingUnit(portionName);
            app.getUserRecipe().setPrepInstructions(instruct);

            ModifyUserRecipesController murc = new ModifyUserRecipesController(app.getUserRecipe(), app.getUser().getUsername());
            murc.execute();
            app.removeUserRecipe();
            app.clearRecipeItems();
            finish();
        }else{
            ArrayList<RecipeItem> copyList = new ArrayList<>();
            copyList.addAll(app.getIngredients());
            app.clearRecipeItems();
            UserRecipe recipe = new UserRecipe(foodName, -42, copyList, portion, portionName, instruct);

            SendUserRecipeController surc = new SendUserRecipeController(recipe,this, app.getUser().getUsername());
            surc.execute();


            finish();
        }



    }

}
