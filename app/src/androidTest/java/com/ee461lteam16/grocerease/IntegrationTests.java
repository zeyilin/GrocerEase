package com.ee461lteam16.grocerease;

/**
 * Created by kassiknight on 11/30/16.
 */

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntegrationTests {

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void AddRecipeIngredientsToGroceryList_Test() {

    }

    @Test
    public void FilterRecipesByCategory_Test() {

    }

    @Test
    public void MoveGroceryItemsToInventory_Test() {

    }
}
