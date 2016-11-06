package sqlite_model;

/**
 * Created by pascalequeralt on 11/5/16.
 */

public class Ingredient {

    int id;
    String name;
    String aisle;

    public Ingredient() {
    }

    public Ingredient(int id, String name, String aisle) {
        this.id = id;
        this.name = name;
        this.aisle = aisle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
}
