package com.example.ak.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import Controllers.SendUserController;

public class FirstStartActivity extends AppCompatActivity {
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    public void nextStart(View view) {
        // Do something in response to button
        EditText text = (EditText) findViewById(R.id.editUser);
        app.addUser(new UserProfile(text.getText().toString(), "", 0,0,0,UserProfile.gender.MALE));
        SendUserController sendUserController = new SendUserController(this, "insertUser", app.getUser(), app.getConstraint() );
        sendUserController.execute();
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }
}
