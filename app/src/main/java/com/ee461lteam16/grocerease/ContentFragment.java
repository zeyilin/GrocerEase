package com.ee461lteam16.grocerease;

import android.support.v4.app.Fragment;
/**
 * Created by Chris on 11/25/16.
 */

public abstract class ContentFragment extends Fragment {

    private String title;

    public ContentFragment() {
        super();
        this.title = "no title";
    }

    public ContentFragment(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
