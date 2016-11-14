package com.ee461lteam16.grocerease;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import java.util.*;
import org.json.*;
import java.io.*;
import android.content.res.AssetManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.jayway.jsonpath.JsonPath;


public class BrowseRecipes extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_recipes);

        ArrayList<String> test = parseRecipes();

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, test);

        ListView listView = (ListView) findViewById(R.id.grocery_list);
        listView.setAdapter(adapter);


    }


    public ArrayList<String> parseRecipes(){

        ArrayList<String> recipeInformation = new ArrayList<String>();

        try{
            JSONObject recipes = new JSONObject(loadJSONFromAsset());
            String recipes_string = recipes.toString();

            recipeInformation = JsonPath.read(recipes_string, "$.recipes[:10][*].title");


        } catch (JSONException e){

            recipeInformation.add("Error loading recipes.");

        }

        return recipeInformation;


    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            //AssetManager mngr = getAssets();
            //InputStream is = mngr.open("recipes.json");
            //InputStream is = getActivity().getAssets().open("recipes.json");
            AssetManager assetManager = getResources().getAssets();
            InputStream is = null;


            is = assetManager.open("recipes.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return json;
        }
        return json;
    }



}
