package com.example.ak.mealplanner.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ak.mealplanner.Models.FoodItem;
import com.example.ak.mealplanner.Models.RecipeItem;
import com.example.ak.mealplanner.R;

import java.util.ArrayList;
import java.util.Comparator;

public class RecipeCartActivity extends AppCompatActivity {

    private MyApplication app;
    private ArrayAdapter<RecipeItem> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_cart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        app = (MyApplication) getApplicationContext();

        final ListView mainListView = findViewById(R.id.list_ingredients);

        // Create ArrayAdapter
        listAdapter  = new ArrayAdapter<RecipeItem>(this, R.layout.listrow, app.getIngredients());


        listAdapter.sort(new Comparator<RecipeItem>() {
            @Override
            public int compare(RecipeItem o1, RecipeItem o2) {
                return o1.getFoodItem().getName().compareTo(o2.getFoodItem().getName());
            }
        });
        listAdapter.notifyDataSetChanged();

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);
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
