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
import android.widget.TextView;
import android.widget.ListAdapter;
import android.widget.Button;
import java.util.ArrayList;

public class InventoryItemAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Ingredient> Inventory = new ArrayList<Ingredient>();
    private Context context;

    public InventoryItemAdapter(ArrayList<Ingredient> inventory, Context context) {
        this.Inventory = inventory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Inventory.size();
    }

    @Override
    public Object getItem(int pos) {
        return Inventory.get(pos);
    }

    @Override
    public long getItemId(int post) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_inventory, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.text1);

        //listItemText.setText(Inventory.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

       /* deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Inventory.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });*/

        return view;
    }
}