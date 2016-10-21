import java.util.*;

/**
 * Created by pascalequeralt on 10/16/16.
 */

public class Recipe {

    String title;
    String description;
    String cuisine;
    List<String> shortDescriptions; //used for filtering

    List<Ingredient> ingredients;

    public String convertUnit(List<Ingredient> ingredients, unit)
      for (ingredient : ingredients) {
        convert unit for each ingredient to specified unit in parameter
      }
    );
    public String getTitle(){
        return this.title;
    };
    public String getDescription(){
        return this.description;
    };
    public String getCuisine(){
        return this.cuisine;
    };

    public List<Ingredient> getIngredients(){
        return this.ingredients;
    };

}
