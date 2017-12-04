package com.example.ak.mealplanner.Adapters;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ak.mealplanner.Activities.MainActivity;
import com.example.ak.mealplanner.Activities.MyApplication;
import com.example.ak.mealplanner.Activities.UserRecipeActivity;
import com.example.ak.mealplanner.Models.UserRecipe;
import com.example.ak.mealplanner.R;

import java.util.Comparator;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RecipeViewHolder> {
    final MainActivity activity;

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        TextView info;
        UserRecipe recipe;
        final MainActivity activity;

        public RecipeViewHolder(View itemView, final MainActivity activity) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView)itemView.findViewById(R.id.recipeCardName);
            info = (TextView)itemView.findViewById(R.id.recipeCardInfo);
            this.activity = activity;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // item clicked
                    Intent intent = new Intent(activity, UserRecipeActivity.class);
                    intent.putExtra("userRecipe", recipe);
                    MyApplication app = (MyApplication) activity.getApplicationContext();
                    app.setUserRecipe(recipe);
                    activity.startActivity(intent);

                }
            });
        }


    }

    private List<UserRecipe> recipes;

    public RVAdapter(List<UserRecipe> recipes, MainActivity activity){
        this.recipes = recipes;
        this.activity = activity;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        RecipeViewHolder pvh = new RecipeViewHolder(v, activity);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder recipeViewHolder, int i) {
        recipeViewHolder.title.setText(recipes.get(i).getName());
        recipeViewHolder.info.setText(recipes.get(i).toString());
        recipeViewHolder.recipe = recipes.get(i);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void add(UserRecipe recipe){
        this.recipes.add(recipe);
    }

    public void clear(){
        this.recipes.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sort(){
        this.recipes.sort(new Comparator<UserRecipe>() {
            @Override
            public int compare(UserRecipe o1, UserRecipe o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
