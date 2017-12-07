package com.example.ak.mealplanner.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ak.mealplanner.Models.FoodItem;
import com.example.ak.mealplanner.Models.MealItem;
import com.example.ak.mealplanner.R;

/**
 * Created by wbigari on 12/7/17.
 */

public class MealItemAdapter extends ArrayAdapter<MealItem> {
    public MealItemAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            MealItem mealItem = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.mi_adapter_row, parent, false);
            }
            // Lookup view for data population
            TextView Name = (TextView) convertView.findViewById(R.id.miName);
            TextView amount = (TextView) convertView.findViewById(R.id.miAmount);
            // Populate the data into the template view using the data object
            FoodItem fi = (FoodItem) mealItem.getFoodItem();
            Name.setText(fi.getName());

            String servingsText = "" + mealItem.getNumServings() + " - " + fi.getServingSize();
            amount.setText(servingsText);
            // Return the completed view to render on screen
            return convertView;
        }
    }
