package com.example.ak.mealplanner.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ak.mealplanner.Adapters.PagerAdapter;
import com.example.ak.mealplanner.Models.UserProfile;
import com.example.ak.mealplanner.R;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {

    MyApplication app;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), bottomNavigationView.getMaxItemCount()));

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_builder:
                                viewPager.setCurrentItem(0);
                                item.setChecked(true);
                                break;
                            case R.id.action_recipe:
                                viewPager.setCurrentItem(1);
                                item.setChecked(true);
                                break;
                            case R.id.action_history:
                                viewPager.setCurrentItem(2);
                                item.setChecked(true);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        app = (MyApplication) getApplicationContext();

        pref = getSharedPreferences("login", Context.MODE_PRIVATE);

        if(pref.getBoolean("LOGIN", false)) {
            FileInputStream fileIn;
            ObjectInputStream objectIn;
            UserProfile userProfile = new UserProfile();

            try {
                fileIn = openFileInput("AboutActivity");
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
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_cart){
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_user){
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_favorite){
            Intent intent = new Intent(this, FavoritesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startFavorites(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }

    public void startGoals(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, GoalsActivity.class);
        startActivity(intent);
    }

    public void buildMeals(View view) {
        // Do something in response to button
        if(app.isEmpty()){
            return;
        }
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }

    public void buildRecipe(View view) {
        Intent intent = new Intent(this, RecipeBuildActivity.class);
        startActivity(intent);
    }

    /**
    public void sendMessage(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void textUpdate(String x){
        TextView text = (TextView) findViewById(R.id.textView4);
        text.setText(text.getText() +  " " + x);
    }
    */
}
