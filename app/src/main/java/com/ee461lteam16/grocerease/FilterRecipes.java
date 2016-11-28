package com.ee461lteam16.grocerease;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pascalequeralt on 11/28/16.
 */

public class FilterRecipes {

    String cheap = "Cheap";
    String dairyFree = "Dairy Free";
    String glutenFree = "Gluten Free";
    String vegan = "Vegan";
    String vegetarian = "Vegetarian";
    String veryHealthy = "Healthy";
    String veryPopular = "Popular";
    String mexican = "Mexican";
    String asian = "Asian";
    String american = "American";
    String middleEastern = "Middle Eastern";
    String european = "European";
    String southern = "Southern";
    String mediterranean = "Mediterranean";
    String african = "African";

    List<String> filterItems;
    String[] labels = {"cheap", "dairyFree", "glutenFree", "vegan", "vegetarian",
            "veryHealthy", "veryPopular", "mexican", "asian", "american", "middle eastern", "european",
            "southern", "mediterranean", "african"};

    public FilterRecipes(){

        filterItems = Arrays.asList(cheap, dairyFree, glutenFree, vegan, vegetarian,
                veryHealthy, veryPopular, mexican, asian, american, middleEastern, european,
                southern, mediterranean, african);

    }

    public List<String> getFilterItems(){
        return this.filterItems;
    }

    public String getQueryString(boolean[] selected){

        int numSelected = 0;
        List<String> selectedFilters = new ArrayList<String>();
        List<String> selectedCuisines = new ArrayList<String>();
        for (int i = 0; i < selected.length; i++){
            if (selected[i] == true){
                numSelected++;
                if (i < 7){
                    selectedFilters.add(labels[i]);
                } else {
                    selectedCuisines.add(labels[i]);
                }
            }
        }

        StringBuilder query = new StringBuilder();

        if (numSelected > 0) {
            query.append("?(");

            for (int j = 0; j < selectedFilters.size(); j++) {
                if (query.length() != ("?(").length()) {
                    query.append("&&");
                }
                query.append("@." + selectedFilters.get(j) + "==true");
            }
            for (int j = 0; j < selectedCuisines.size(); j++) {
                if (query.length() != ("?(").length()) {
                    query.append("&&");
                }
                //query.append("@.cuisines.indexOf($['" + selectedCuisines.get(j) + "'])!=(-1)");
                query.append("@.cuisines[0]=='" + selectedCuisines.get(j) + "'");
            }

            query.append(")");
        } else {
            query.append("*");
        }


        return query.toString();

    }

}
