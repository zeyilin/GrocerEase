import java.util.*;

/**
 * Created by pascalequeralt on 10/16/16.
 */

public class Ingredient {

    String name;
    double quantity;
    //enum unitName;
    String description;
    int ID;
    double price;


    public String getName(){
        return this.name;
    };
    public double getQuantity(){
        return this.quantity;
    };

    //public enum getUnit(){};

    public void convertUnit(){};
    public String getDescription(){
        return this.description;
    };
    public void changeDescription(){};
    public int getID(){
        return this.ID;
    };
    public double getPrice(){
        return this.price;
    };

}
