package com.ee461lteam16.grocerease;

/**
 * Created by kassiknight on 11/27/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IngredientListAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Ingredient> IngredientList = new ArrayList<Ingredient>();
    private Context context;

    public IngredientListAdapter(ArrayList<Ingredient> ingredientList, Context context) {
        this.IngredientList = ingredientList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return IngredientList.size();
    }

    @Override
    public Object getItem(int pos) {
        return IngredientList.get(pos);
    }

    @Override
    public long getItemId(int post) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Ingredient item = (Ingredient) getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grocery_row, parent, false);
        }

        // Lookup view for data population
        TextView item_name = (TextView) convertView.findViewById(R.id.item_name);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.check_grocery_item);
        checkBox.setChecked(false);
        // Populate the data into the template view using the data object
        item_name.setText(item.toString());

        // Return the completed view to render on screen
        return convertView;

    }

    public void remove(int position){
        IngredientList.remove(position);
        notifyDataSetChanged();
    }

}