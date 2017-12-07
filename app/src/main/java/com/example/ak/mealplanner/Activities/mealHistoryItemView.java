package com.example.ak.mealplanner.Activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ak.mealplanner.Adapters.MealItemAdapter;
import com.example.ak.mealplanner.Models.FoodItem;
import com.example.ak.mealplanner.Models.MealItem;
import com.example.ak.mealplanner.Models.MealPlannerRec;
import com.example.ak.mealplanner.R;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by wbigari on 12/7/17.
 */

public class mealHistoryItemView extends AppCompatActivity {
    MealPlannerRec mealPlannerRec;
    MyApplication app;
    ListView breakfastlv,lunchlv,dinnerlv;
    MealItemAdapter breakfastl, lunchl, dinnerl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        mealPlannerRec = (MealPlannerRec) intent.getSerializableExtra("mealplan");

        lunchlv =  this.findViewById(R.id.mh_l_list);
        breakfastlv = this.findViewById(R.id.mh_b_list);
        dinnerlv = this.findViewById(R.id.mh_d_list);

        breakfastl  = new MealItemAdapter(this, R.layout.listrow);
        lunchl = new MealItemAdapter(this, R.layout.listrow);
        dinnerl = new MealItemAdapter(this, R.layout.listrow);

        for(MealItem fi : mealPlannerRec.getBreakfastItems()){
            breakfastl.add(fi);
        }
        breakfastl.sort(new Comparator<MealItem>() {
            @Override
            public int compare(MealItem o1, MealItem o2) {
                return o1.getFoodItem().getName().compareTo(o2.getFoodItem().getName());
            }
        });
        breakfastl.notifyDataSetChanged();
        for(MealItem fi : mealPlannerRec.getLunchItems()){
            lunchl.add((MealItem)fi);
        }
        lunchl.sort(new Comparator<MealItem>() {
            @Override
            public int compare(MealItem o1, MealItem o2) {
                return o1.getFoodItem().getName().compareTo(o2.getFoodItem().getName());
            }
        });
        lunchl.notifyDataSetChanged();
        for(MealItem fi : mealPlannerRec.getDinnerItems()){
            dinnerl.add((MealItem)fi);
        }
        dinnerl.sort(new Comparator<MealItem>() {
            @Override
            public int compare(MealItem o1, MealItem o2) {
                return o1.getFoodItem().getName().compareTo(o2.getFoodItem().getName());
            }
        });
        dinnerl.notifyDataSetChanged();

        breakfastlv.setAdapter(breakfastl);
        lunchlv.setAdapter(lunchl);
        dinnerlv.setAdapter(dinnerl);

        app = (MyApplication) getApplicationContext();

        TextView x = (TextView)findViewById(R.id.mealPlanHistString);
        x.setText(mealPlannerRec.contstraintsString());

        breakfastlv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MealItem m = (MealItem) parent.getItemAtPosition(position);
                FoodItem x = (FoodItem) m.getFoodItem();
                Intent intent = new Intent(mealHistoryItemView.this, FoodActivity.class);
                intent.putExtra("foodItem", x);
                startActivity(intent);

            }
        });
        lunchlv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MealItem m = (MealItem) parent.getItemAtPosition(position);
                FoodItem x = (FoodItem) m.getFoodItem();
                Intent intent = new Intent(mealHistoryItemView.this, FoodActivity.class);
                intent.putExtra("foodItem", x);
                startActivity(intent);

            }
        });
        dinnerlv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MealItem m = (MealItem) parent.getItemAtPosition(position);
                FoodItem x = (FoodItem) m.getFoodItem();
                Intent intent = new Intent(mealHistoryItemView.this, FoodActivity.class);
                intent.putExtra("foodItem", x);
                startActivity(intent);
            }
        });

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
}
