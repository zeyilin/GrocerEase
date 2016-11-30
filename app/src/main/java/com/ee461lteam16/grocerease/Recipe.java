package com.ee461lteam16.grocerease;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pascalequeralt on 11/5/16.
 */


@JsonDeserialize(using = ItemDeserializer.class)
public class Recipe implements Serializable {

    long id;
    String title;
    int minutes;
    int servings;
    String instructions;
    String imageURL;

    boolean cheap;
    boolean dairyFree;
    boolean glutenFree;
    boolean vegan;
    boolean vegetarian;
    boolean veryHealthy;
    boolean veryPopular;

    List<String> cuisines;

    List<Ingredient> ingredientList;

    boolean favorited;
    int missingIngreds;

    public Recipe(long id, String title, int minutes, int servings, String instructions, String imageURL,
                  boolean cheap, boolean dairyFree, boolean glutenFree, boolean vegan, boolean vegetarian,
                  boolean veryHealthy, boolean veryPopular, List<String> cuisines, List<Ingredient> ingredientList,
                  int missingIngreds) {
        this.id = id;
        this.title = title;
        this.minutes = minutes;
        this.servings = servings;
        this.instructions = instructions;
        this.imageURL = imageURL;
        this.cheap = cheap;
        this.dairyFree = dairyFree;
        this.glutenFree = glutenFree;
        this.vegan = vegan;
        this.vegetarian = vegetarian;
        this.veryHealthy = veryHealthy;
        this.veryPopular = veryPopular;
        this.cuisines = cuisines;
        this.ingredientList = ingredientList;

        if(BrowseRecipesFragment.favorites.contains(this.id)){
            this.favorited = true;
        } else {
            this.favorited = false;
        }

        this.missingIngreds = missingIngreds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isCheap() {
        return cheap;
    }

    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVeryHealthy() {
        return veryHealthy;
    }

    public void setVeryHealthy(boolean veryHealthy) {
        this.veryHealthy = veryHealthy;
    }

    public boolean isVeryPopular() {
        return veryPopular;
    }

    public void setVeryPopular(boolean veryPopular) {
        this.veryPopular = veryPopular;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public int getMissingIngreds(){ return missingIngreds; }

    public String getReadyInString(){
        return "Total Time: " + getMinutes() + " min";
    }

    public String getServingsString(){
        return "Servings: " + getServings();
    }

    public String getMissingIngredsString() {return "Missing Ingredients: " + getMissingIngreds(); }

}
