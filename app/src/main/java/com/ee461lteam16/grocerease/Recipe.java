package com.ee461lteam16.grocerease;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by pascalequeralt on 11/5/16.
 */


@JsonDeserialize(using = ItemDeserializer.class)
public class Recipe {

    int id;
    String title;
    int minutes;
    int servings;

    public Recipe() {
    }

    public Recipe(int id, String title, int minutes, int servings) {
        this.id = id;
        this.title = title;
        this.minutes = minutes;
        this.servings = servings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
