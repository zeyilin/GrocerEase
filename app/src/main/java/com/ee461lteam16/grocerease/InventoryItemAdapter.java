package com.ee461lteam16.grocerease;

/**
 * Created by kassiknight on 11/27/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

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
        View view = getView(position, convertView, parent);
        TextView title = (TextView) view.findViewById(R.id.text1);
        Button deleteButton = (Button) view.findViewById(R.id.delete_btn);

        final Ingredient item = (Ingredient) this.getItem(position);

        title.setText(item.getName());
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Inventory.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;



        /*View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.inventory_item, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.text1);
        System.out.println(listItemText);

        Ingredient item = (Ingredient) getItem(position);
        //listItemText.setText("Test");

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

      /* deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                //Inventory.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });*/

    }
}