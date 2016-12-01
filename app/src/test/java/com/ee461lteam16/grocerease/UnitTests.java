package com.ee461lteam16.grocerease;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    Ingredient testIngredient1;
    Ingredient testIngredient2;
    Ingredient testIngredient3;
    InventoryFragment testInventoryFragment;
    GroceryListFragment testGroceryListFragment;
    ArrayList<Ingredient> testInventory;
    ArrayList<Ingredient> testGroceryList;


    @Before
    public void Initialize() {
        testIngredient1 = new Ingredient(12, "flour", "used in most baked goods", "cups");
        testIngredient2 = new Ingredient("milk", 2, "gallons");
        testIngredient3 = new Ingredient("bananas", 6, "");

        testInventory = new ArrayList<>();
        testInventory.add(testIngredient1);
        testInventory.add(testIngredient2);
        testGroceryList = new ArrayList<>();
        testGroceryList.add(testIngredient3);
    }

    @Test
    public void Ingredient1ToString_isCorrect() throws Exception {
        assertEquals("12.0 cups flour", testIngredient1.toString());
    }

    @Test
    public void Ingredient2ToString_isCorrect() throws Exception {
        assertEquals("2.0 gallons milk", testIngredient2.toString());
    }

    @Test
    public void AddIngredientsInventory_isCorrect() throws Exception {
        testInventoryFragment.addIngredients(testInventory);
        assertEquals(testInventoryFragment.Inventory, testInventory);
    }

    @Test
    public void AddIngredientsGroceryList_isCorrect() throws Exception {
        testGroceryListFragment.addIngredients(testGroceryList);
        assertEquals(testGroceryListFragment.groceryList, testGroceryList);

    }

    // Not sure if this is a unit test ??
    @Test
    public void getFilteredRecipes_isCorrect() throws Exception {

    }
}
