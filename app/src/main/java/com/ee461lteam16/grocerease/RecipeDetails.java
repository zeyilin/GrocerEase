package com.ee461lteam16.grocerease;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pascalequeralt on 11/21/16.
 */

public class RecipeDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_details);

        Recipe currentRecipe = (Recipe) getIntent().getSerializableExtra("currentRecipe");

        TextView title = (TextView) findViewById(R.id.recipeTextTitle);
        title.setText(currentRecipe.getTitle());

        ImageView image = (ImageView) findViewById(R.id.recipeImageView);
        Picasso.with(this).load(currentRecipe.getImageURL()).into(image);

        TextView ingredientsHeader = (TextView) findViewById(R.id.ingredientsHeader);
        ingredientsHeader.setText("Ingredients");

        ListView ingredients = (ListView) findViewById(R.id.textIngredients);
        List<Ingredient> ingredientList = currentRecipe.getIngredientList();

        ArrayAdapter<Ingredient> adapter =
                new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_1, ingredientList) {

                    // Called to map each data element to a view within the list
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        TextView ingredientStr = (TextView) view.findViewById(android.R.id.text1);

                        Ingredient ingredient = (Ingredient) this.getItem(position);

                        ingredientStr.setText(ingredient.getDescription());

                        return view;
                    }
                };


        ingredients.setAdapter(adapter);

        ListHelper.getListViewSize(ingredients);

        TextView stepsHeader = (TextView) findViewById(R.id.stepsHeader);
        stepsHeader.setText("Directions");

        TextView summary = (TextView) findViewById(R.id.layoutSteps);
        summary.setText(currentRecipe.getInstructions());

    }

}
