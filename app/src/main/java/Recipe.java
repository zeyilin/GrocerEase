import java.util.*;

/**
 * Created by pascalequeralt on 10/16/16.
 */

public class Recipe {

    int id;
    String title;
    String description;

    //List<Ingredient> ingredients;

    public Recipe(){}

    public Recipe(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

}
