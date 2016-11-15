package com.ee461lteam16.grocerease;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BrowseRecipes extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_browse_recipes);

        ArrayList<Recipe> recipeList = getRecipes();

        /*Temporary fix - display titles from recipeList in list view
        Later, update Listview XML to incorporate servings and minutes*/
        ArrayList<String> recipeListStr = new ArrayList<String>();
        for (Recipe r: recipeList) {
            recipeListStr.add(r.getTitle());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipeListStr);

        ListView listView = (ListView) findViewById(R.id.grocery_list);

        listView.setAdapter(adapter);

    }


    public ArrayList<Recipe> getRecipes(){

        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        try{
            JSONObject json = new JSONObject(loadJSONFromAsset());
            String jsonStr = json.toString();

            Configuration conf = Configuration.builder()
                    .mappingProvider(new JacksonMappingProvider())
                    .jsonProvider(new JacksonJsonProvider())
                    .build();

            TypeRef<ArrayList<Recipe>> type = new TypeRef<ArrayList<Recipe>>(){};

            //On first load, display all recipes (no filters applied)
            recipeList = JsonPath
                    .using(conf)
                    .parse(jsonStr)
                    .read("$.recipes[*][*]", type);

        } catch (JSONException e){

            recipeList.add(new Recipe(-1, "Error loading recipes.", -1, -1));

        }

        return recipeList;

    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
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