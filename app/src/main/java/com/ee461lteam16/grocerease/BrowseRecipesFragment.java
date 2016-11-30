package com.ee461lteam16.grocerease;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Chris on 11/25/16.
 */

public class BrowseRecipesFragment extends ContentFragment {

    public final String TAG = "BrowseRecipesFragment";
    public ArrayList<Recipe> recipeList = new ArrayList<>();
    public static ArrayList<Recipe> searchRecipeList = new ArrayList<>();
    public static ArrayAdapter<Recipe> adapter;
    public FilterRecipes filterRecipes;
    public static ArrayList<Long> favorites = new ArrayList<>();
    protected FirebaseAuth mAuth;
    protected FirebaseAuth.AuthStateListener mAuthListener;
    SwipeRefreshLayout refresh;

    public BrowseRecipesFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        LinearLayout llLayout    = (LinearLayout)    inflater.inflate(R.layout.fragment_browse_recipes, container, false);
        firebaseInit();
        return llLayout;
    }

    public static void updateList(){
        Collections.sort(searchRecipeList, new SortByFavorite());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        firebaseInit();
        recipeList.clear();
        recipeList.addAll(getRecipes());
        searchRecipeList.clear();
        searchRecipeList.addAll(getRecipes());
        Collections.sort(searchRecipeList, new SortByFavorite());

        adapter =
                new ArrayAdapter<Recipe>(this.getContext(), R.layout.recipe_list, R.id.Recipe_title, searchRecipeList) {

                    // Called to map each data element to a view within the list
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);

                        TextView title = (TextView) view.findViewById(R.id.Recipe_title);
                        TextView minutes = (TextView) view.findViewById(R.id.minutes);
                        TextView servings = (TextView) view.findViewById(R.id.servings);
                        TextView missingIngreds = (TextView) view.findViewById(R.id.missingIngreds);
                        ImageView image = (ImageView) view.findViewById(R.id.Recipe_icon);
                        ImageView favorite = (ImageView) view.findViewById(R.id.Recipe_favorited);

                        Recipe recipe = (Recipe) searchRecipeList.get(position);

                        title.setText(recipe.getTitle());
                        minutes.setText(recipe.getReadyInString());
                        servings.setText(recipe.getServingsString());
                        missingIngreds.setText(recipe.getMissingIngredsString());
                        Picasso.with(view.getContext()).load(recipe.getImageURL()).placeholder(view.getContext().getResources().getIdentifier("@drawable/grocerease", null, "com.ee461lteam16.grocerease")).into(image);

                        if (recipe.isFavorited()) {
                            favorite.setVisibility(View.VISIBLE);

                            String uri = "@drawable/filled_heart";  // where myresource (without the extension) is the file

                            int imageResource = getResources().getIdentifier(uri, null, "com.ee461lteam16.grocerease");
                            Drawable res = getResources().getDrawable(imageResource);

                            favorite.setImageDrawable(res);
                        } else {
                            favorite.setVisibility(View.GONE);
                        }

                        return view;
                    }

                    private RecipeSearch recipeSearch;
                    @Override
                    public Filter getFilter() {
                        if (recipeSearch == null) {
                            recipeSearch = new RecipeSearch();
                        }

                        return recipeSearch;
                    }

                };

        // Find the list and attach the ArrayAdapter to it
        Activity myActivity = this.getActivity();
        ListView listView = (ListView) myActivity.findViewById(R.id.recipe_list_view);
        listView.setAdapter(adapter);

        TextView emptyText = (TextView) myActivity.findViewById(R.id.recipes_empty);
        listView.setEmptyView(emptyText);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(), RecipeDetails.class);
                intent.putExtra("index", position);
                intent.putExtra("currentRecipe", searchRecipeList.get(position));
                startActivity(intent);

            }
        });

        SearchView search = (SearchView) myActivity.findViewById(R.id.recipe_search);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                adapter.getFilter().filter(text);
                return false;
            }
        });

        MultiSpinner multiSpinner = (MultiSpinner) myActivity.findViewById(R.id.multi_spinner_filter);

        filterRecipes = new FilterRecipes();
        List<String> items = filterRecipes.getFilterItems();

        multiSpinner.setItems(items, getString(R.string.for_all), onSelectedListener);

        refresh = (SwipeRefreshLayout) myActivity.findViewById(R.id.swipe_refresh_layout);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refresh.setRefreshing(true);

                searchRecipeList.clear();
                searchRecipeList.addAll(getRecipes());
                Collections.sort(searchRecipeList, new SortByFavorite());
                adapter.notifyDataSetChanged();

                refresh.setRefreshing(false);
            }
        });


    }

    private void firebaseInit() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            String dbRecipesID = mAuth.getCurrentUser().getUid() + "_Recipes";
            //String dbInventoryID = mAuth.getCurrentUser().getUid() + "_Inventory";
            //String dbGroceryListID = mAuth.getCurrentUser().getUid() + "_GroceryList";
            DatabaseReference mFavesRef = FirebaseDatabase.getInstance().getReference(dbRecipesID);
            //DatabaseReference mInventoryRef = FirebaseDatabase.getInstance().getReference(dbInventoryID);
            //DatabaseReference mGroceryListRef = FirebaseDatabase.getInstance().getReference(dbGroceryListID);

            mFavesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        BrowseRecipesFragment.favorites = (ArrayList<Long>) dataSnapshot.getValue();
                    }
                    Log.d("favorite recipes: ", BrowseRecipesFragment.favorites.toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {

            String query = filterRecipes.getQueryString(selected);
            System.out.println(query);

            ArrayList<Recipe> filteredRecipes = getFilteredRecipes(query);

            recipeList.clear();
            recipeList.addAll(filteredRecipes);

            searchRecipeList.clear();
            searchRecipeList.addAll(filteredRecipes);

            adapter.notifyDataSetChanged();
        }
    };

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

    public ArrayList<Recipe> getFilteredRecipes(String query){

        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        try{
            JSONObject json = new JSONObject(loadJSONFromAsset());
            String jsonStr = json.toString();

            Object dataObject = JsonPath.parse(jsonStr).read("$.recipes[*][?(@.glutenFree == false)]");
            String test = dataObject.toString();

            System.out.println(test.substring(0, 1000));

            Configuration conf = Configuration.builder()
                    .mappingProvider(new JacksonMappingProvider())
                    .jsonProvider(new JacksonJsonProvider())
                    .build();

            TypeRef<ArrayList<Recipe>> type = new TypeRef<ArrayList<Recipe>>(){};

            //On first load, display all recipes (no filters applied)
            recipeList = JsonPath
                    .using(conf)
                    .parse(jsonStr)
                    .read("$.recipes[*][" + query + "]", type);

        } catch (JSONException e){
        }

        Collections.sort(recipeList, new SortByFavorite());
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

    private class RecipeSearch extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            System.out.println("TRIES TO FILTER RECIPES");

            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = recipeList;
                results.count = recipeList.size();
            }
            else {
                // We perform filtering operation
                ArrayList<Recipe> newRecipeList = new ArrayList<Recipe>();

                for (Recipe r : recipeList){
                    if (r.getTitle().toUpperCase().contains(constraint.toString().toUpperCase())){
                        newRecipeList.add(r);
                    }
                }

                results.values = newRecipeList;
                results.count = newRecipeList.size();

            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,FilterResults results) {
            // Now we have to inform the adapter about the new list filtered
            searchRecipeList.clear();
            searchRecipeList.addAll((ArrayList<Recipe>) results.values);

            Collections.sort(searchRecipeList, new SortByFavorite());

            System.out.println(searchRecipeList);
            adapter.notifyDataSetChanged();
        }
    }
}


