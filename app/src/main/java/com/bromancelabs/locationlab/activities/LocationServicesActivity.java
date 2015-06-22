package com.bromancelabs.locationlab.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.bromancelabs.locationlab.R;
import com.bromancelabs.locationlab.fragments.LocationServicesFragment;

public class LocationServicesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle(R.string.location_services);

        setBackNavigation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Fragment createFragment() {
        return LocationServicesFragment.newInstance();
    }
}
