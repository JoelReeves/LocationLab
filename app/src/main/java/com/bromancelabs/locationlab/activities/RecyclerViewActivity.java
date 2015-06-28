package com.bromancelabs.locationlab.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bromancelabs.locationlab.R;
import com.bromancelabs.locationlab.fragments.RecyclerViewFragment;

public class RecyclerViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle(R.string.toolbar_choose_api);
    }

    @Override
    protected Fragment createFragment() {
        return RecyclerViewFragment.newInstance();
    }
}
