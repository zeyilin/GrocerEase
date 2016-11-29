package com.ee461lteam16.grocerease;

import java.util.Comparator;

/**
 * Created by pascalequeralt on 11/28/16.
 */

public class SortByFavorite implements Comparator<Recipe> {
    @Override
    public int compare(Recipe r1, Recipe r2) {
        return (r1.isFavorited() ^ r2.isFavorited()) ? ((r1.isFavorited() ^ true) ? 1 : -1) : 0;
    }
}
