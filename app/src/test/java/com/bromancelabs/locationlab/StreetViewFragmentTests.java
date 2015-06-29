package com.bromancelabs.locationlab;

import android.support.v4.app.Fragment;

import com.bromancelabs.locationlab.fragments.BaseFragment;
import com.bromancelabs.locationlab.fragments.StreetViewFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class StreetViewFragmentTests {

    private Fragment fragment;
    private SupportStreetViewPanoramaFragment mapFragment;

    @Before
    public void setup() throws Exception {
        fragment = StreetViewFragment.newInstance();
        startFragment(fragment);

        mapFragment = (SupportStreetViewPanoramaFragment) fragment.getChildFragmentManager()
                .findFragmentById(R.id.map);
    }

    @Test
    public void fragmentNotNull() throws Exception {
        assertNotNull(fragment);
        assertTrue(fragment instanceof BaseFragment);
    }

    @Test
    public void mapNotNull() throws Exception {
        assertNotNull(mapFragment);
    }
}
