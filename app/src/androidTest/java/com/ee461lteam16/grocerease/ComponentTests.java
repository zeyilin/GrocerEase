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
public class ComponentTests {

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void AddInventoryItem_Test() {

    }

    @Test
    public void DeleteInventoryItem_Test() {

    }

    @Test
    public void OpenRecipeDetails_Test() {

    }

    // Might be too complicated to navigate sign-in
    @Test
    public void SignOut_Test() {

    }

}