package com.ee461lteam16.grocerease;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

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

        TextView summary = (TextView) findViewById(R.id.recipeTextSummary);
        summary.setText(Html.fromHtml(currentRecipe.getInstructions()));

    }

}
