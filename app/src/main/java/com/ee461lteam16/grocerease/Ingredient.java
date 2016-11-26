package com.ee461lteam16.grocerease;

import java.io.Serializable;

/**
 * Created by pascalequeralt on 11/5/16.
 */

public class Ingredient implements Serializable {

    double amount;
    String name;
    String description;
    String unit;
    boolean inInventory;

    public Ingredient(double amount, String name, String description, String unit) {
        this.amount = amount;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.inInventory = false; //fix this later on when comparing recipe ingredients to inventory
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isInInventory() {
        return inInventory;
    }

    public void setInInventory(boolean inInventory) {
        this.inInventory = inInventory;
    }
}
