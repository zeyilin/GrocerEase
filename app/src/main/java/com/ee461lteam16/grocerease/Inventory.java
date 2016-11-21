package com.ee461lteam16.grocerease;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;


public class Inventory extends AppCompatActivity {

    ArrayList<String> shoppingList = null;
    ArrayAdapter<String> adapter = null;
    ListView lv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        shoppingList = new ArrayList<>();
        Collections.addAll(shoppingList, "Eggs", "Yogurt", "Milk", "Bananas", "Apples", "Tide with bleach", "Cascade");
        shoppingList.addAll(Arrays.asList("Napkins", "Dog food", "Chapstick", "Bread"));
        shoppingList.add("Sunscreen");
        shoppingList.add("Toothpaste");
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, shoppingList);
        //lv = (ListView) findViewById(R.id.activity_inventory);
        lv.setAdapter(adapter);
    }
}
