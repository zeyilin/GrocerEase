package com.ee461lteam16.grocerease;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Chris on 11/28/16.
 */

public class GroceryListFragment extends ContentFragment {


    public final String TAG = "BrowseRecipesFragment";
    public ArrayList<Recipe> recipeList = new ArrayList<>();
    public ArrayList<Recipe> searchRecipeList = new ArrayList<>();
    public ArrayAdapter<Recipe> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        LinearLayout llLayout    = (LinearLayout)    inflater.inflate(R.layout.fragment_grocery_list, container, false);
        return llLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

}


