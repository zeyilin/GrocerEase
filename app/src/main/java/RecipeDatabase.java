import java.util.*;

/**
 * Created by pascalequeralt on 10/16/16.
 */

public class RecipeDatabase {

    HashMap<Integer, Recipe> recipeDatabase;

    public void updateRecipes(){};
    public RecipeDatabase exportRecipes(){
        return this;
    };
    public Recipe generateRecipe(){
        return this.recipeDatabase.get(0);      // filler return statement
    };

}
