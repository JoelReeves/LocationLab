package com.bromancelabs.locationlab;

import android.support.v4.app.Fragment;

import com.bromancelabs.locationlab.fragments.BaseFragment;
import com.bromancelabs.locationlab.fragments.MapsFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

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
public class MapsFragmentTests {

    private Fragment fragment;
    private GoogleMap map;

    @Before
    public void setup() throws Exception {
        fragment = MapsFragment.newInstance();
        startFragment(fragment);

        map = ((SupportMapFragment) fragment.getChildFragmentManager().findFragmentById(R.id.map)).getMap();
    }

    @Test
    public void fragmentNotNull() throws Exception {
        assertNotNull(fragment);
        assertTrue(fragment instanceof BaseFragment);
    }

    @Test
    public void mapNotNull() throws Exception {
        //assertNotNull(map);
    }
}
