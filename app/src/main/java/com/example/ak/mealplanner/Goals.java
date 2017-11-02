package com.example.ak.mealplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Goals extends AppCompatActivity {
    MyApplication app;
    EditText calMin, calMax;
    EditText carbMin, carbMax;
    EditText protMin, protMax;
    EditText fatMin, fatMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        app = (MyApplication) getApplicationContext();

        calMin = (EditText)findViewById(R.id.calMin);
        calMax = (EditText)findViewById(R.id.calMax);
        carbMin = (EditText)findViewById(R.id.carbMin);
        carbMax = (EditText)findViewById(R.id.carbMax);
        protMin = (EditText)findViewById(R.id.protMin);
        protMax = (EditText)findViewById(R.id.protMax);
        fatMin = (EditText)findViewById(R.id.fatMin);
        fatMax = (EditText)findViewById(R.id.fatMax);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void saveGoals(View view){
        if(calMin.getText().toString().equals("") || calMax.getText().toString().equals("") || carbMin.getText().toString().equals("") || carbMax.getText().toString().equals("") || protMin.getText().toString().equals("") || protMax.getText().toString().equals("") || fatMin.getText().toString().equals("") || fatMax.getText().toString().equals("")){
            finish();
            return;
        }
        app.addConstraint(new Constraints(Integer.parseInt(calMin.getText().toString()), Integer.parseInt(calMax.getText().toString()), Integer.parseInt(carbMin.getText().toString()), Integer.parseInt(carbMax.getText().toString()), Integer.parseInt(protMin.getText().toString()), Integer.parseInt(protMax.getText().toString()), Integer.parseInt(fatMin.getText().toString()), Integer.parseInt(fatMax.getText().toString())));
        finish();
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
