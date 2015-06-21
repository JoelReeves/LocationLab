package com.bromancelabs.locationlab;

import android.app.Activity;

import com.bromancelabs.locationlab.activities.BaseActivity;
import com.bromancelabs.locationlab.activities.LocationServicesActivity;
import com.bromancelabs.locationlab.activities.RecyclerViewActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ActivityTests {

    private Activity recyclerViewActivity;
    private Activity locationServicesActivity;

    @Before
    public void setup() throws Exception {
        recyclerViewActivity = Robolectric.buildActivity(RecyclerViewActivity.class)
                .create()
                .start()
                .resume()
                .get();

        locationServicesActivity = Robolectric.buildActivity(LocationServicesActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void recyclerViewActivityNotNull() throws Exception {
        assertNotNull(recyclerViewActivity);
        assertTrue(recyclerViewActivity instanceof BaseActivity);
    }

    @Test
    public void locationServicesActivityNotNull() throws Exception {
        assertNotNull(locationServicesActivity);
        assertTrue(locationServicesActivity instanceof BaseActivity);
    }
}
