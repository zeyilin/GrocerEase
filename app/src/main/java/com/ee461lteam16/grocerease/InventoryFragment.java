package com.ee461lteam16.grocerease;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Chris on 11/25/16.
 */

public class InventoryFragment extends ContentFragment {

    public final String TAG = "InventoryFragment";
    public static ArrayList<Ingredient> Inventory = new ArrayList<>();
    ListView lv = null;

    Ingredient temp_ingred;
    String temp_ingred_name;
    String temp_ingred_quantity;
    public static InventoryListAdapter adapter;

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

        Collections.sort(Inventory);
        adapter = new InventoryListAdapter(Inventory, myContext);
        lv = (ListView) this.getView().findViewById(R.id.inventoryList);
        lv.setAdapter(adapter);

        TextView emptyText = (TextView)this.getView().findViewById(R.id.inventory_empty);
        lv.setEmptyView(emptyText);

        FloatingActionButton addButton = (FloatingActionButton) view.findViewById(R.id.add_to_inventory);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                System.out.println(Inventory.get(0).getName());

                AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
                builder.setTitle(getString(R.string.add_item_prompt));
                final EditText input = new EditText(myContext);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        temp_ingred_name = input.getText().toString();

                        AlertDialog.Builder quantityAlert = new AlertDialog.Builder(myContext);
                        quantityAlert.setTitle(getString(R.string.add_quant_prompt));
                        final EditText quantityInput = new EditText(myContext);
                        quantityAlert.setView(quantityInput);
                        quantityAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        temp_ingred_quantity = quantityInput.getText().toString();
                                        String[] quant_unit = temp_ingred_quantity.split(" ");

                                        long val = 0;
                                        String unit = "";
                                        if (quant_unit.length == 1){
                                            val = Long.parseLong(quant_unit[0]);
                                        }
                                        if (quant_unit.length == 2){
                                            val = Long.parseLong(quant_unit[0]);
                                            unit = quant_unit[1];
                                        }

                                        temp_ingred = new Ingredient(temp_ingred_name, val, unit);
                                        Inventory.add(temp_ingred);
                                        adapter.notifyDataSetChanged();                                    }

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

        Button delete = (Button) view.findViewById(R.id.delete_from_inventory);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                deleteSelected();

            }
        });

    }

    public void deleteSelected(){

        List<Integer> toRemove = new ArrayList<>();
        for (int i = 0; i < Inventory.size(); i++){
            View view = lv.getChildAt(i);
            CheckBox checked = (CheckBox) view.findViewById(R.id.check_grocery_item);
            if (checked.isChecked()){
                toRemove.add(i);
            }
            checked.setChecked(false);
        }

        for (int i = toRemove.size() - 1; i >= 0; i--){

            int index = toRemove.get(i);
            Inventory.remove(index);

        }


        adapter.notifyDataSetChanged();

    }

    public static ArrayList<Ingredient> getInventory(){
        return Inventory;
    }

    public  Ingredient getInventoryItem(int index){
        return Inventory.get(index);
    }

    public static void addIngredients(List<Ingredient> add){
        Inventory.addAll(add);
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

}