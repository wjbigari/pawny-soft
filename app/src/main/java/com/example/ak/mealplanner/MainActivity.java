package com.example.ak.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ak.mealplanner.Models.UserProfile;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import Controllers.GetUserRecipesController;

public class MainActivity extends AppCompatActivity {

    MyApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity
            startActivity(new Intent(MainActivity.this, FirstStartActivity.class));
            Toast.makeText(MainActivity.this, "First Run", Toast.LENGTH_LONG)
                    .show();
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        tabLayout.setupWithViewPager(viewPager);

        app = (MyApplication) getApplicationContext();

        if(!isFirstRun) {
            FileInputStream fileIn;
            ObjectInputStream objectIn;
            UserProfile userProfile = new UserProfile();

            try {
                fileIn = openFileInput("profile");
                objectIn = new ObjectInputStream(fileIn);

                userProfile = (UserProfile) objectIn.readObject();
                objectIn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            app.addUser(userProfile);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_cart){
            Intent intent = new Intent(this, Cart.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_user){
            Intent intent = new Intent(this, EditProfile.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startBook(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, RecipeBook.class);
        startActivity(intent);
    }

    public void startFavorites(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Favorites.class);
        startActivity(intent);
    }

    public void startGoals(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Goals.class);
        startActivity(intent);
    }

    public void buildMeals(View view) {
        // Do something in response to button
        if(app.isEmpty()){
            return;
        }
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
    }

    public void buildRecipe(View view) {
        Intent intent = new Intent(this, RecipeBuildActivity.class);
        startActivity(intent);
    }

    /**
    public void sendMessage(View view) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

    public void textUpdate(String x){
        TextView text = (TextView) findViewById(R.id.textView4);
        text.setText(text.getText() +  " " + x);
    }
    */
}
