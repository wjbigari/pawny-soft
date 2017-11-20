package com.example.ak.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Controllers.LoginController;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication)getApplication();
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        username = (EditText) findViewById(R.id.editUserLogin);
        password = (EditText)findViewById(R.id.editPassword);
    }

    public void loadProfile(View view){
        String uname, pass;
        uname = username.getText().toString();
        pass = password.getText().toString();

        LoginController lc = new LoginController(uname,pass,app, this);
        lc.execute();

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
    @Override
    public void finish(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.finish();
    }

}
