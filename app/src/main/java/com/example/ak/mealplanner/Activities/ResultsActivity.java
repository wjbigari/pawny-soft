package com.example.ak.mealplanner.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ak.mealplanner.Controllers.MealPlannerController;
import com.example.ak.mealplanner.Controllers.SendMealPlannerRecController;
import com.example.ak.mealplanner.Models.MealPlannerRec;
import com.example.ak.mealplanner.Adapters.CustomAdapter;
import com.example.ak.mealplanner.R;

public class ResultsActivity extends AppCompatActivity {

    private CustomAdapter mAdapter;
    private  MyApplication app;
    private MealPlannerRec mpr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new CustomAdapter(this);

        ListView list = (ListView) findViewById(R.id.listRec);


        app = (MyApplication) getApplicationContext();

        try {
            MealPlannerController mpc = new MealPlannerController(app.getConstraint(), app.getList(), mAdapter, mpr, this);
            mpc.execute();
        }catch (Exception e){
            Toast.makeText(this, "Meal Build Error: Aborted", Toast.LENGTH_SHORT).show();
            finish();
        }

        list.setAdapter(mAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void setMpr(MealPlannerRec mpr){
        this.mpr = mpr;
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

    public void addToFavorite(View view){
        if(mpr != null) {
            SendMealPlannerRecController smprc = new SendMealPlannerRecController(app.getUser().getUsername(), mpr, null);
            smprc.execute();
            finish();
        }
        else{
            return;
        }
    }

    public void redoBuild(View view){
        if(mpr == null){
            return;
        }
        MealPlannerController mpc = new MealPlannerController(app.getConstraint(), app.getList(), mAdapter,mpr, this);
        mpc.execute();
    }
}
