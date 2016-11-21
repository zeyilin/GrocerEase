package com.ee461lteam16.grocerease;

/**
 * Created by Chris on 11/21/16.
 */


import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.ViewGroup;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class TabFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container,
                             Bundle instanceSavedState) {
        return inflator.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle instanceSavedState) {

    }
}
