import java.util.HashMap;

/**
 * Created by pascalequeralt on 10/16/16.
 */

public class IngredientDatabase {

    HashMap<Integer, Ingredient> ingredientDatabase;

    public void addToDatabase(){};
    public Ingredient findIngredient(){
        return this.ingredientDatabase.get(0);      // filler return statement
    }

}
