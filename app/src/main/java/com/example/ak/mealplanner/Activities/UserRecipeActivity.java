package com.example.ak.mealplanner.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Comparator;

import com.example.ak.mealplanner.Models.MealItem;
import com.example.ak.mealplanner.Models.RecipeItem;
import com.example.ak.mealplanner.Models.UserRecipe;
import com.example.ak.mealplanner.R;

import com.example.ak.mealplanner.Controllers.DeleteRecipeItemController;

public class UserRecipeActivity extends AppCompatActivity {
    UserRecipe recipe;

    private ArrayAdapter<RecipeItem> listAdapter;

    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();

        recipe = (UserRecipe) intent.getSerializableExtra("userRecipe");

        final ListView mainListView = findViewById(R.id.userrecipeList);

        // Create ArrayAdapter
        listAdapter  = new ArrayAdapter<RecipeItem>(this, R.layout.listrow, recipe.getIngredients());

        listAdapter.sort(new Comparator<RecipeItem>() {
            @Override
            public int compare(RecipeItem o1, RecipeItem o2) {
                return o1.getFoodItem().getName().compareTo(o2.getFoodItem().getName());
            }
        });
        listAdapter.notifyDataSetChanged();

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecipeItem item = (RecipeItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(UserRecipeActivity.this, RecipeActivity.class);
                intent.putExtra("foodItem", item.getFoodItem());
                startActivity(intent);
            }
        });

        app = (MyApplication) getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipeitem_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                app.removeUserRecipe();
                finish();
                break;
            case R.id.action_delete_recipe:
                deleteUserRecipe();
                break;
            case R.id.action_update_recipe:
                updateUserRecipe();
                break;
        }
        return true;
    }

    @Override
    public void finish(){
        super.finish();
    }

    @Override
    public void onResume(){
        super.onResume();
        listAdapter.clear();
        listAdapter.addAll(app.getUserRecipe().getIngredients());
        listAdapter.sort(new Comparator<RecipeItem>() {
            @Override
            public int compare(RecipeItem o1, RecipeItem o2) {
                return o1.getFoodItem().getName().compareTo(o2.getFoodItem().getName());
            }
        });
        listAdapter.notifyDataSetChanged();
    }

    public void deleteUserRecipe(){
        try{
            DeleteRecipeItemController dric = new DeleteRecipeItemController(app.getUserRecipe().getFoodId());
            dric.execute();
            app.removeUserRecipe();
            finish();
        }catch(Exception e){

        }

    }

    public void updateUserRecipe(){
        Intent intent = new Intent(this, RecipeSaveActivity.class);
        startActivity(intent);
        finish();
    }

    public void modifyRecipeIngredients(View view){
        Intent intent = new Intent(this, RecipeBuildActivity.class);
        startActivity(intent);
    }

    public void addRecipeBreakfast(View view){
        app.addBreakfast(new MealItem(recipe, false, MealItem.Meal.BREAKFAST));
    }

    public void addRecipeLunch(View view){
        app.addLunch(new MealItem(recipe, false, MealItem.Meal.LUNCH));
    }

    public void addRecipeDinner(View view){
        app.addDinner(new MealItem(recipe, false, MealItem.Meal.DINNER));
    }
}
