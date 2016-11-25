package com.ee461lteam16.grocerease;

/**
 * Created by Chris on 11/21/16.
 */


import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.Log;
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

    /**
     * This class represents a tab to be displayed by {@link ViewPager} and it's associated
     * {@link SlidingTabLayout}.
     */
    static class SamplePagerItem {
        private final CharSequence title;
        private final Class<ContentFragment> contentClass;

        SamplePagerItem(CharSequence title, Class<ContentFragment> c) {
            this.title = title;
            contentClass = c;
        }

        /**
         * @return A new {@link Fragment} to be displayed by a {@link ViewPager}
         */
        ContentFragment createFragment() {
            // If the constructor of ContentFragment changes, this will break
            try {
                return contentClass.getDeclaredConstructor(String.class).newInstance(title);
            } catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage());
            }
        }

        /**
         * @return the title which represents this tab. In this sample this is used directly by
         * {@link android.support.v4.view.PagerAdapter#getPageTitle(int)}
         */
        CharSequence getTitle() {
            return title;
        }
    }

    static final String LOG_TAG = "TabFragment";
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;


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


