package com.bromancelabs.locationlab;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.bromancelabs.locationlab.fragments.BaseFragment;
import com.bromancelabs.locationlab.fragments.SingleLocationFragment;
import com.bromancelabs.locationlab.support.ResourceLocator;

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
public class SingleLocationFragmentTests {
    private Fragment fragment;
    private TextView headerText;
    private TextView latitude;
    private TextView longitude;

    @Before
    public void setup() throws Exception {
        fragment = SingleLocationFragment.newInstance();
        startFragment(fragment);

        headerText = (TextView) getViewById(R.id.txtLocationHeader);
        latitude = (TextView) getViewById(R.id.txtLatitude);
        longitude = (TextView) getViewById(R.id.txtLongitude);
    }

    @Test
    public void fragmentNotNull() throws Exception {
        assertNotNull(fragment);
        assertTrue(fragment instanceof BaseFragment);
    }


    @Test
    public void shouldHaveHeaderText() throws Exception {
        assertViewIsVisible(headerText);
        assertEquals(getViewString(headerText), ResourceLocator.getString(R.string.device_location));
    }

    @Test
    public void latituteTextIsVisible() throws Exception {
        assertViewIsVisible(latitude);
    }

    @Test
    public void longitudeTextIsVisible() throws Exception {
        assertViewIsVisible(longitude);
    }

    private View getViewById(int id) {
        return fragment.getView().findViewById(id);
    }

    private String getViewString(TextView view) {
        return view.getText().toString();
    }
}
