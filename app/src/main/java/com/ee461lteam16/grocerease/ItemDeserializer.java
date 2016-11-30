package com.ee461lteam16.grocerease;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascalequeralt on 11/14/16.
 */

public class ItemDeserializer extends StdDeserializer<Recipe> {

    public ItemDeserializer() {
        this(null);
    }

    public ItemDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Recipe deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);

        long id = (node.get("id")).longValue();
        String title = node.get("title").asText();
        int minutes = (Integer) (node.get("readyInMinutes")).numberValue();
        int servings = (Integer) (node.get("servings")).numberValue();
        String instructions = node.get("instructions").asText();
        String imageURL = node.get("image").asText();

        boolean cheap = (Boolean) node.get("cheap").asBoolean();
        boolean dairyFree = (Boolean) node.get("dairyFree").asBoolean();
        boolean glutenFree = (Boolean) node.get("glutenFree").asBoolean();
        boolean vegan = (Boolean) node.get("vegan").asBoolean();
        boolean vegetarian = (Boolean) node.get("vegetarian").asBoolean();
        boolean veryHealthy = (Boolean) node.get("veryHealthy").asBoolean();
        boolean veryPopular = (Boolean) node.get("veryPopular").asBoolean();

        JsonNode cuisineArray = node.get("cuisines");
        List<String> cuisines = new ArrayList<String>();
        if (cuisineArray.isArray()){
            for (JsonNode c : cuisineArray){
                cuisines.add(c.asText());
            }
        }

        JsonNode ingredientArray = node.get("extendedIngredients");
        List<Ingredient> ingredientList = new ArrayList<>();
        if (ingredientArray.isArray()){
            for (JsonNode i : ingredientArray){

                double amount = i.get("amount").doubleValue();
                String name = i.get("name").asText();
                String description = i.get("originalString").asText();
                String unit = i.get("unit").asText();

                ingredientList.add(new Ingredient(amount, name, description, unit));

            }
        }

        int missingIngredients = ingredientList.size();
        List<Ingredient> inventory = InventoryFragment.getInventory();
        for (Ingredient i: inventory){

            String name = i.getName().toLowerCase();
            double val = i.getAmount();
            String unit = i.getUnit().toLowerCase();

            for (Ingredient j: ingredientList){
                if (j.getName().toLowerCase().equals(name) &&
                        j.getAmount() <= val &&
                        j.getUnit().toLowerCase().equals(unit)) {
                    missingIngredients--;
                    i.setInInventory(true);
                } else {
                    i.setInInventory(false);
                }
            }

        }

        return new Recipe(id, title, minutes, servings, instructions, imageURL, cheap,
                dairyFree, glutenFree, vegan, vegetarian, veryHealthy, veryPopular, cuisines, ingredientList,
                missingIngredients);

    }

}