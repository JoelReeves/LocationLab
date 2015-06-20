package com.bromancelabs.locationlab.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bromancelabs.locationlab.R;
import com.bromancelabs.locationlab.fragments.LocationServicesFragment;

public class LocationServicesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle(R.string.location_services);
    }

    @Override
    protected Fragment createFragment() {
        return LocationServicesFragment.newInstance();
    }
}
