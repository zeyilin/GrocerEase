package com.ee461lteam16.grocerease;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * Created by Chris on 11/25/16.
 */

public class BrowseRecipesFragment extends ContentFragment {

    public final String TAG = "BrowseRecipesFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        FragmentActivity faActivity  = super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        LinearLayout llLayout    = (LinearLayout)    inflater.inflate(R.layout.fragment_browse_recipes, container, false);

        return llLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        final ArrayList<Recipe> recipeList = getRecipes();

        ArrayAdapter<Recipe> adapter =
                new ArrayAdapter<Recipe>(this.getContext(), R.layout.recipe_list, R.id.Recipe_title, recipeList) {

                    // Called to map each data element to a view within the list
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        TextView title = (TextView) view.findViewById(R.id.Recipe_title);
                        TextView minutes = (TextView) view.findViewById(R.id.minutes);
                        TextView servings = (TextView) view.findViewById(R.id.servings);
                        ImageView image = (ImageView) view.findViewById(R.id.Recipe_icon);

                        final Recipe recipe = (Recipe) this.getItem(position);

                        title.setText(recipe.getTitle());
                        minutes.setText(recipe.getReadyInString());
                        servings.setText(recipe.getServingsString());
                        Picasso.with(view.getContext()).load(recipe.getImageURL()).placeholder(view.getContext().getResources().getDrawable(android.R.drawable.star_on)).into(image);
                        return view;
                    }
                };

        Log.d(TAG, "Adapter is: " + adapter.toString());

        // Find the list and attach the ArrayAdapter to it
        Activity myActivity = this.getActivity();
        ListView listView = (ListView) myActivity.findViewById(R.id.recipe_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(), RecipeDetails.class);
                intent.putExtra("currentRecipe", recipeList.get(position));
                startActivity(intent);

            }
        });

        EditText myFilter = (EditText) this.getActivity().findViewById(R.id.recipe_search);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //dataAdapter.getFilter().filter(s.toString());
            }
        } );
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
