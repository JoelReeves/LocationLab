package com.bromancelabs.locationlab;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.bromancelabs.locationlab.fragments.BaseFragment;
import com.bromancelabs.locationlab.fragments.ContinuousLocationFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static com.bromancelabs.locationlab.support.Assert.assertViewIsVisible;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ContinuousLocationFragmentTests {

    private Fragment fragment;
    private TextView locationHeader;
    private TextView latitude;
    private TextView longitude;
    private TextView updatedTime;

    @Before
    public void setup() throws Exception {
        fragment = ContinuousLocationFragment.newInstance();
        startFragment(fragment);

        locationHeader = (TextView) getViewById(R.id.txtLocationHeader);
        latitude = (TextView) getViewById(R.id.txtLatitude);
        longitude = (TextView) getViewById(R.id.txtLongitude);
        updatedTime = (TextView) getViewById(R.id.txtUpdatedTime);
    }

    @Test
    public void fragmentNotNull() throws Exception {
        assertNotNull(fragment);
        assertTrue(fragment instanceof BaseFragment);
    }

    @Test
    public void shouldHaveHeaderText() throws Exception {
        assertViewIsVisible(locationHeader);
        assertEquals(getViewString(locationHeader), "Device's Location updated every 5 seconds");
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

    private View getViewById(int id) {
        return fragment.getView().findViewById(id);
    }

    private String getViewString(TextView view) {
        return view.getText().toString();
    }
}
