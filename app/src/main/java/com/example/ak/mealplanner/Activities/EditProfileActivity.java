package com.example.ak.mealplanner.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.ak.mealplanner.Models.UserProfile;
import com.example.ak.mealplanner.R;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import com.example.ak.mealplanner.Controllers.SendUserController;

public class EditProfileActivity extends AppCompatActivity {

    MyApplication app;
    UserProfile user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        app = (MyApplication) getApplication();
        user = app.getUser();

        EditText x = (EditText) findViewById(R.id.editName);
        x.setText(user.getName());
        x = (EditText) findViewById(R.id.editAge);
        if(Integer.parseInt(user.getAge()) > 0) {
            x.setText(user.getAge());
        }
        x = (EditText) findViewById(R.id.editHeight);
        if(Integer.parseInt(user.getHeight()) > 0) {
            x.setText(user.getHeight());
        }
        x = (EditText) findViewById(R.id.editWeight);
        if(Integer.parseInt(user.getWeight()) > 0) {
            x.setText(user.getWeight());
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.genderGroup);
        if(user.getGen() == UserProfile.gender.MALE) {
            radioGroup.check(R.id.radioMale);
        }
        else{
            radioGroup.check(R.id.radioFemale);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if(checkedId == R.id.radioMale){
                    user.setGen(UserProfile.gender.MALE);
                }
                else{
                    user.setGen(UserProfile.gender.FEMALE);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                save();
                finish();
                break;
        }
        return true;
    }

    public void startGoals(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, GoalsActivity.class);
        startActivity(intent);
    }

    public void saveProfile(View view) {
        // Do something in response to button
        save();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void loadEditUser(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void save(){
        UserProfile profile = app.getUser();

        EditText x = (EditText) findViewById(R.id.editName);
        if(!x.getText().toString().equals("")) {
            profile.setName(x.getText().toString());
        }
        x = (EditText) findViewById(R.id.editAge);
        if(!x.getText().toString().equals("")) {
            profile.setAge(Integer.parseInt(x.getText().toString()));
        }
        x = (EditText) findViewById(R.id.editHeight);
        if(!x.getText().toString().equals("")) {
            profile.setHeight(Integer.parseInt(x.getText().toString()));
        }
        x = (EditText) findViewById(R.id.editWeight);
        if(!x.getText().toString().equals("")) {
            profile.setWeight(Integer.parseInt(x.getText().toString()));
        }
        FileOutputStream fileOut;
        ObjectOutputStream objectOut;

        try {
            fileOut = openFileOutput("AboutActivity", Context.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(profile);
            objectOut.close();
            try{
                SendUserController sendUserController = new SendUserController(this, "updateUser", profile, null);
                sendUserController.execute();
            }catch (Exception e){

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
