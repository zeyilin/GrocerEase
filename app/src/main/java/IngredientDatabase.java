import java.util.HashMap;

/**
 * Created by pascalequeralt on 10/16/16.
 */

public class IngredientDatabase {

    HashMap<Integer, Ingredient> ingredientDatabase;

    public void addToDatabase();
    public Ingredient findIngredient();
    public void populateDatabase(); // initial population of database from scraping

}
