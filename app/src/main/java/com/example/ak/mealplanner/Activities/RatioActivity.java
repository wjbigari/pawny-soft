package com.example.ak.mealplanner.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ak.mealplanner.R;

public class RatioActivity extends AppCompatActivity {
    private TextView carbRatio, protRatio, fatRatio;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratio);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        carbRatio = (TextView) findViewById(R.id.carbRatio);
        protRatio = (TextView) findViewById(R.id.protRatio);
        fatRatio = (TextView) findViewById(R.id.fatRatio);

        app = (MyApplication) getApplicationContext();
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

    public void saveRatio(View view){
        if(Integer.parseInt(carbRatio.getText().toString()) + Integer.parseInt(carbRatio.getText().toString()) + Integer.parseInt(carbRatio.getText().toString()) != 100){
            Toast.makeText(this, "Must add up to 100", Toast.LENGTH_SHORT).show();
            return;
        }
        app.getConstraint();
        finish();
    }
}
