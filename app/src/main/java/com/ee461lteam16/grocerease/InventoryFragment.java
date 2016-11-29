package com.ee461lteam16.grocerease;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Chris on 11/25/16.
 */

public class InventoryFragment extends ContentFragment {

    public final String TAG = "InventoryFragment";
    ArrayList<Ingredient> shoppingList = new ArrayList<>();
    ArrayAdapter adapter = null;
    ListView lv = null;
    Ingredient ingred_1 = new Ingredient("12 dozen", "eggs");
    Ingredient ingred_2 = new Ingredient("2 gallons", "milk");
    Ingredient ingred_3 = new Ingredient("6 lbs", "turkey");
    Ingredient temp_ingred;
    String temp_ingred_name;
    String temp_ingred_quantity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.getActivity().setTheme(R.style.AppTheme);
        RelativeLayout llLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_inventory, container, false);
        return llLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final Context myContext = this.getContext();
        shoppingList.add(ingred_1);
        shoppingList.add(ingred_2);
        shoppingList.add(ingred_3);

        Collections.sort(shoppingList);
        //final ListAdapter adapter = new ArrayAdapter<>(myContext, android.R.layout.simple_list_item_1, shoppingList);
        //final ListAdapter adapter = new InventoryItemAdapter(shoppingList, myContext);
        lv = (ListView) this.getView().findViewById(R.id.listView);
        lv.setAdapter(adapter);

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
                                        shoppingList.add(temp_ingred);
                                        Collections.sort(shoppingList);
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

    public ArrayList<Ingredient> getInventory(){
        return shoppingList;
    }

    public Ingredient getInventoryItem(int index){
        return shoppingList.get(index);
    }

}