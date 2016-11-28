package com.ee461lteam16.grocerease;

/**
 * Created by kassiknight on 11/27/16.
 */

import java.util.List;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

// Custom list item class for menu items
public class InventoryItemAdapter extends BaseAdapter {

    private List<Ingredient> inventory;
    private Context context;
    private int numItems = 0;

    public InventoryItemAdapter(final List<Ingredient> inventory, Context context) {
        this.inventory = inventory;
        this.context = context;
        this.numItems = inventory.size();
    }

    public int getCount() {
        return numItems;
    }

    public Ingredient getItem(int position) {
        return inventory.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the current list item
        final Ingredient item = inventory.get(position);
        // Get the layout for the list item
        final RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.inventory_item, parent, false);

        // Set the text label as defined in our list item
        TextView txtLabel = (TextView) itemLayout.findViewById(R.id.text1);
        txtLabel.setText(item.getName());

        return itemLayout;
    }

}