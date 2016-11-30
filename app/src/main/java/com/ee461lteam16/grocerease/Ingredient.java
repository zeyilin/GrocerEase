package com.ee461lteam16.grocerease;

import java.io.Serializable;

/**
 * Created by pascalequeralt on 11/5/16.
 */

public class Ingredient implements Serializable, Comparable<Ingredient> {

    double amount;
    String name;
    String description;
    String amountInUnits;
    String unit;
    boolean inInventory;

    public Ingredient(double amount, String name, String description, String unit) {
        this.amount = amount;
        this.name = name;
        this.description = description;
        this.unit = unit;
    }

    public Ingredient(String name, long amount, String unit){
        this.name = name;
        this.amount = amount;
        this.unit = unit;

        // REFERENCE INGREDIENT DATABASE TO FIND UNITS OF COMMON INGREDIENTS
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

    public String getAmountInUnits() { return amountInUnits; }

    public boolean isInInventory() {
        return inInventory;
    }

    public void setInInventory(boolean inInventory) {
        this.inInventory = inInventory;
    }

    public int compareTo(Ingredient other){

        return this.getName().compareToIgnoreCase(other.getName());
    }

    @Override
    public String toString(){
        return this.amount + " " + this.unit + " " + this.name;
    }
}