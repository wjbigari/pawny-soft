package com.example.ak.mealplanner.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ak.mealplanner.Controllers.GetFavoritesController;
import com.example.ak.mealplanner.Models.MealItem;
import com.example.ak.mealplanner.R;

import java.util.ArrayList;

import com.example.ak.mealplanner.Controllers.SearchController;

public class FavoritesActivity extends AppCompatActivity {
    private SearchController searchController;

    private ArrayAdapter<MealItem> listAdapter;

    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        app = (MyApplication) getApplication();

        final ListView mainListView = findViewById(R.id.favoritesList);

        // Create and populate a List of food names
        ArrayList<MealItem> foodList = new ArrayList<MealItem>();

        // Create ArrayAdapter
        listAdapter  = new ArrayAdapter<MealItem>(this, R.layout.listrow, foodList);

        //TODO Will- use the controller to get the favorite meals
        try{
            GetFavoritesController gfc = new GetFavoritesController(app.getUser().getUsername(),listAdapter);
            gfc.execute();
        }catch(Exception e){

        }

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
