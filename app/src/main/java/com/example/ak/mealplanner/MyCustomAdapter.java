package com.example.ak.mealplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by ak on 10/26/17.
 */

public class MyCustomAdapter {
    /**
    private ArrayList<FoodItem> list = new ArrayList<FoodItem>();
    private Context context;



    public MyCustomAdapter(ArrayList<FoodItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }


    public boolean add(FoodItem item){
        list.add(item);
        return true;
    }

    public void addList(ArrayList<FoodItem> list){
        this.list = list;
    }

    public void clear(){
        list.clear();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listrow, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).toString());

        //Handle buttons and add onClickListeners
        Button breakfast = (Button)view.findViewById(R.id.breakfButton);
        Button lunch = (Button)view.findViewById(R.id.lunchButton);
        Button dinner = (Button)view.findViewById(R.id.dinnerButton);

        breakfast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity)context;
                main.addItem(new MealItem(list.get(position), MealItem.Meal.BREAKFAST));
            }
        });
        lunch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity)context;
                main.addItem(new MealItem(list.get(position), MealItem.Meal.LUNCH));
            }
        });
        dinner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity)context;
                main.addItem(new MealItem(list.get(position), MealItem.Meal.DINNER));
            }
        });

        return view;
    }
    */
}
