import java.util.*;

/**
 * Created by zeyilin on 10/21/16.
 */

public class GetRecipes implements Command {
	Inventory inventory = new Inventory();

	public Inventory(Inventory inventory) {
		this.Inventory = Inventory();
	}
	public Recipe execute() {
		//PSEUDOCODE:
		return recipe using algorithm to find recipes from ingredients
		in this.Inventory
	}
}
