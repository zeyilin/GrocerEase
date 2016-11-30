package com.ee461lteam16.grocerease;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascalequeralt on 11/21/16.
 */

public class RecipeDetails extends Activity {

    public Recipe currentRecipe;
    public int index;
    public List<Ingredient> ingredientList;
    ListView listview;
    public List<Ingredient> inventoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_details);

        currentRecipe = (Recipe) getIntent().getSerializableExtra("currentRecipe");
        index = (Integer) getIntent().getSerializableExtra("index");

        TextView title = (TextView) findViewById(R.id.recipeTextTitle);
        title.setText(currentRecipe.getTitle());

        ImageView image = (ImageView) findViewById(R.id.recipeImageView);
        Picasso.with(this).load(currentRecipe.getImageURL()).into(image);

        TextView ingredientsHeader = (TextView) findViewById(R.id.ingredientsHeader);
        ingredientsHeader.setText("Ingredients");

        listview = (ListView) findViewById(R.id.textIngredients);
        ingredientList = currentRecipe.getIngredientList();

        ArrayAdapter<Ingredient> adapter =
                new ArrayAdapter<Ingredient>(this, R.layout.ingredient_row, R.id.text_ingredient, ingredientList) {

                    // Called to map each data element to a view within the list
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        TextView ingredientStr = (TextView) view.findViewById(R.id.text_ingredient);

                        Ingredient ingredient = (Ingredient) this.getItem(position);

                        ingredientStr.setText(ingredient.getDescription());

                        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_ingredient);

                        boolean toCheck = findInInventory(ingredient);

                        if (toCheck){
                            checkBox.setChecked(false);
                        } else {
                            checkBox.setChecked(true);
                        }

                        return view;
                    }
                };


        listview.setAdapter(adapter);




        ListHelper.getListViewSize(listview);

        TextView stepsHeader = (TextView) findViewById(R.id.stepsHeader);
        stepsHeader.setText("Directions");

        TextView summary = (TextView) findViewById(R.id.layoutSteps);
        summary.setText(currentRecipe.getInstructions());

        CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
        if (currentRecipe.isFavorited() || BrowseRecipesFragment.favorites.contains(currentRecipe.getId())) {
            favorite.setChecked(true);
        }
        favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    currentRecipe.setFavorited(true);

                    BrowseRecipesFragment.favorites.add(currentRecipe.getId());

                    //set favorite for recipe at index
                    BrowseRecipesFragment.searchRecipeList.get(index).setFavorited(true);

                } else {
                    currentRecipe.setFavorited(false);

                    if (BrowseRecipesFragment.favorites.contains(currentRecipe.getId())){
                        BrowseRecipesFragment.favorites.remove(currentRecipe.getId());
                    }

                    BrowseRecipesFragment.searchRecipeList.get(index).setFavorited(false);

                }
                System.out.println("Trying to update list...");
                BrowseRecipesFragment.updateList();
            }
        });


        Button move = (Button) findViewById(R.id.move_to_list);
        move.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                List<Ingredient> moveIngredients = new ArrayList<Ingredient>();

                for (int i = 0; i < ingredientList.size(); i++){
                    View view = listview.getChildAt(i);
                    CheckBox checked = (CheckBox) view.findViewById(R.id.check_ingredient);
                    if (checked.isChecked()){
                        moveIngredients.add(ingredientList.get(i));
                    }
                }
                System.out.println("INGREDIENTS TO MOVE: " + moveIngredients.size());

                GroceryListFragment.addIngredients(moveIngredients);
            }
        });

    }

    public boolean findInInventory(Ingredient current){


        List<Ingredient> inventory = InventoryFragment.getInventory();
        for (Ingredient i: inventory) {

            String name = i.getName().toLowerCase();
            double val = i.getAmount();
            String unit = i.getUnit().toLowerCase();


            if (current.getName().toLowerCase().equals(name) &&
                    current.getAmount() <= val &&
                    current.getUnit().toLowerCase().equals(unit)) {


                return true;
            }

        }
        return false;


    }

}