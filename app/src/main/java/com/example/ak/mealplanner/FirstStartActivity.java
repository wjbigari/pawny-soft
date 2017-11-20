package com.example.ak.mealplanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.ak.mealplanner.Models.UserProfile;

import Controllers.SendUserController;

public class FirstStartActivity extends AppCompatActivity {
    MyApplication app;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);

        pref = getSharedPreferences("login", Context.MODE_PRIVATE);
        if (pref.getBoolean("LOGIN", false)) {
            //has login
            startActivity(new Intent(this, MainActivity.class));
            //must finish this activity (the login activity will not be shown when click back in main activity)
            finish();
        }

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
        String userName = text.getText().toString();
        text = (EditText) findViewById(R.id.editFirstName);
        String name = text.getText().toString();
        text = (EditText) findViewById(R.id.editNewPassword);
        if(userName.equals("") || name.equals("") || text.getText().toString().equals("")){
            return;
        }
        app.addUser(new UserProfile(userName, name, 0,0,0,UserProfile.gender.MALE));
        SendUserController sendUserController = new SendUserController(this, "insertUser", app.getUser() );
        sendUserController.execute();
        pref.edit().putBoolean("LOGIN", true).apply();
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
        finish();
    }

    public void login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
