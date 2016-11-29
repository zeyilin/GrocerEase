package com.ee461lteam16.grocerease;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Chris on 11/28/16.
 */

public class GroceryListFragment extends ContentFragment {


    public final String TAG = "BrowseRecipesFragment";
    public ArrayList<Recipe> recipeList = new ArrayList<>();
    public ArrayList<Recipe> searchRecipeList = new ArrayList<>();
    public ArrayList<Ingredient> groceryList = new ArrayList<>();
    public ArrayAdapter<Recipe> adapter;

    ListView lv = null;
    Ingredient temp_ingred;
    String temp_ingred_name;
    String temp_ingred_quantity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.getActivity().setTheme(R.style.AppTheme);
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout llLayout    = (RelativeLayout)    inflater.inflate(R.layout.fragment_grocery_list, container, false);
        return llLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final Context myContext = this.getContext();

        Collections.sort(groceryList);
        final ListAdapter adapter = new IngredientListAdapter(groceryList, myContext);
        lv = (ListView) this.getView().findViewById(R.id.groceryList);
        lv.setAdapter(adapter);
        lv.setEmptyView(this.getView().findViewById(android.R.id.empty));

        FloatingActionButton addButton = (FloatingActionButton) view.findViewById(R.id.add_to_inventory);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
                builder.setTitle("Add Item");
                final EditText input = new EditText(myContext);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        temp_ingred_name = input.getText().toString();

                        AlertDialog.Builder quantityAlert = new AlertDialog.Builder(myContext);
                        quantityAlert.setTitle("How many?");
                        final EditText quantityInput = new EditText(myContext);
                        quantityAlert.setView(quantityInput);
                        quantityAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        temp_ingred_quantity = quantityInput.getText().toString();
                                        temp_ingred = new Ingredient(temp_ingred_quantity, temp_ingred_name);
                                        groceryList.add(temp_ingred);
                                        Collections.sort(groceryList);
                                        lv.setAdapter(adapter);
                                    }

                                }
                        );

                        quantityAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        quantityAlert.show();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

    }

}


