package com.ee461lteam16.grocerease;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        SharedPreferences grocereasePrefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLoggedIn = grocereasePrefs.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            intent  = new Intent(this, BrowseRecipes.class);

        }
        else {
            intent = new Intent(this, BrowseRecipes.class);
        }

        //Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}