package com.example.ak.mealplanner.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ak.mealplanner.R;

import com.example.ak.mealplanner.Controllers.LoginController;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    MyApplication app;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication)getApplication();
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("login", Context.MODE_PRIVATE);

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

        if(uname.equals("") || pass.equals("")){
            return;
        }

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
        super.finish();
    }

}
