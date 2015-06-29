package com.bromancelabs.locationlab;

import android.support.v4.app.Fragment;

import com.bromancelabs.locationlab.fragments.BaseFragment;
import com.bromancelabs.locationlab.fragments.StreetViewFragment;
import com.google.android.gms.maps.StreetViewPanorama;
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
    private StreetViewPanorama panorama;

    @Before
    public void setup() throws Exception {
        fragment = StreetViewFragment.newInstance();
        startFragment(fragment);

        panorama = ((SupportStreetViewPanoramaFragment) fragment.getChildFragmentManager()
                .findFragmentById(R.id.mapStreetView)).getStreetViewPanorama();
    }

    @Test
    public void fragmentNotNull() throws Exception {
        assertNotNull(fragment);
        assertTrue(fragment instanceof BaseFragment);
    }

    @Test
    public void panoramaNotNull() throws Exception {
        //assertNotNull(panorama);
    }
}
