package com.example.ak.mealplanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

/**
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Builder");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Builder");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Profile");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Profile");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Calendar");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Calendar");
        host.addTab(spec);

        // Find the ListView resource.
        ListView mainListView = (ListView) findViewById(R.id.list_main);
        ListView mainListView2 = (ListView) findViewById(R.id.list_main2);
        ListView mainListView3 = (ListView) findViewById(R.id.list_main3);

        // Create and populate a List of food names.
        String[] planets = new String[] { "Bread", "Milk", "Eggs", "Bananas", "Sugar", "Pizza", "Tomato", "Yogurt"};
        ArrayList<String> foodList = new ArrayList<String>();
        foodList.addAll( Arrays.asList(planets) );

        // Create ArrayAdapter using the planet list.
        ArrayAdapter<String> listAdapter  = new ArrayAdapter<String>(this, R.layout.listrow, foodList);

        // Add more foods
        listAdapter.add( "Orange" );
        listAdapter.add( "Chicken" );
        listAdapter.add( "Butter" );
        listAdapter.add( "Syrup" );
        listAdapter.add( "Ham" );

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);
        mainListView2.setAdapter(listAdapter);
        mainListView3.setAdapter(listAdapter);

        //mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

}
