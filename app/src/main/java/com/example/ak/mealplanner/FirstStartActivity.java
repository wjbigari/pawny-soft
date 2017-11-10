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
        String userName = text.getText().toString();
        text = (EditText) findViewById(R.id.editFirstName);

        app.addUser(new UserProfile(userName, text.getText().toString(), 0,0,0,UserProfile.gender.MALE));
        if(userName.equals("") || text.getText().toString().equals("")){
            return;
        }
        app.addUser(new UserProfile(userName,text.getText().toString() , 0,0,0,UserProfile.gender.MALE));
        Intent intent = new Intent(this, EditProfile.class);
        SendUserController sendUserController = new SendUserController(this, "insertUser", app.getUser(), app.getConstraint() );
        sendUserController.execute();
        startActivity(intent);
        finish();
    }
}
