package com.bromancelabs.locationlab;

import android.widget.TextView;

import com.bromancelabs.locationlab.fragments.LocationServicesFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static com.bromancelabs.locationlab.support.Assert.assertViewIsVisible;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.util.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LocationServicesTests {

    private LocationServicesFragment fragment;
    private TextView latitude;
    private TextView longitude;
    private TextView updatedTime;

    @Before
    public void setup() throws Exception {
        fragment = LocationServicesFragment.newInstance();
        startFragment(fragment);

        latitude = (TextView) fragment.getView().findViewById(R.id.txtLatitude);
        longitude = (TextView) fragment.getView().findViewById(R.id.txtLongitude);
        updatedTime = (TextView) fragment.getView().findViewById(R.id.txtUpdatedTime);
    }

    @Test
    public void fragmentNotNull() throws Exception {
        assertNotNull(fragment);
        assertTrue(fragment instanceof LocationServicesFragment);
    }

    @Test
    public void latituteTextIsVisible() throws Exception {
        assertViewIsVisible(latitude);
    }

    @Test
    public void longitudeTextIsVisible() throws Exception {
        assertViewIsVisible(longitude);
    }

    @Test
    public void updatedTimeTextIsVisible() throws Exception {
        assertViewIsVisible(updatedTime);
    }
}
