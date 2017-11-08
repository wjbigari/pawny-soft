package com.example.ak.mealplanner;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;

import Controllers.SearchController;

public class RecipeBuildActivity extends AppCompatActivity {

    private SearchController searchController;

    private ArrayAdapter<FoodItem> listAdapter;

    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_build);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ListView mainListView = findViewById(R.id.listFood);

        // Create and populate a List of food names
        ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();

        // Create ArrayAdapter
        listAdapter  = new ArrayAdapter<FoodItem>(this, R.layout.listrow, foodList);


        listAdapter.sort(new Comparator<FoodItem>() {
            @Override
            public int compare(FoodItem o1, FoodItem o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        listAdapter.notifyDataSetChanged();

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodItem x = (FoodItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(RecipeBuildActivity.this, RecipeActivity.class);
                intent.putExtra("foodItem", x);
                startActivity(intent);

            }
        });

        EditText inputSearch = (EditText) findViewById(R.id.foodSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

                if(searchController!= null && searchController.getStatus()== AsyncTask.Status.RUNNING){
                    searchController.cancel(true);
                }
                searchController = new SearchController(cs.toString(), listAdapter);

                searchController.execute();

                //listAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });

        app = (MyApplication) getApplication();
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

    public void saveRecipe(View view) {
        if(app.hasIngredients()) {
            Intent intent = new Intent(this, RecipeSaveActivity.class);
            startActivity(intent);
        }
        else{
            return;
        }
    }
}
