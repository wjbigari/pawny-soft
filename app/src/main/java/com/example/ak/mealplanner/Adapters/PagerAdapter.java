package com.example.ak.mealplanner.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ak.mealplanner.Activities.BuilderFragment;
import com.example.ak.mealplanner.Activities.HistoryFragment;
import com.example.ak.mealplanner.Activities.RecipeFragment;

/**
 * Created by ak on 10/14/17.
 */


public class PagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;


    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new BuilderFragment();
            case 1:
                return new RecipeFragment();
            case 2:
                return new HistoryFragment();
            default:
                return new BuilderFragment();
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Builder";
            case 1:
                return "Recipes";
            case 2:
                return "History";
            default:
                return "Meal Planner";
        }
    }
}
