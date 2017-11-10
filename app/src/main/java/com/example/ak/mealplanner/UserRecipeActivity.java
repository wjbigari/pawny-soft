package com.example.ak.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;

public class UserRecipeActivity extends AppCompatActivity {
    UserRecipe recipe;

    private ArrayAdapter<RecipeItem> listAdapter;

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

        TextView text = (TextView) findViewById(R.id.recipeName);

        text.setText(recipe.getName());

        text = (TextView) findViewById(R.id.recipeInfo);

        text.setText(recipe.toString());

        final ListView mainListView = findViewById(R.id.userrecipeList);

        ArrayList<UserRecipe> foodList = new ArrayList<UserRecipe>();

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
