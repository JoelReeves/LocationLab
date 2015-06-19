package com.bromancelabs.locationlab;

import android.widget.TextView;

import com.bromancelabs.locationlab.activities.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static com.bromancelabs.locationlab.support.Assert.assertViewIsVisible;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTests {

    private MainActivity activity;
    private TextView latitude;
    private TextView longitude;
    private TextView updatedTime;

    @Before
    public void setup() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .get();

        latitude = (TextView) activity.findViewById(R.id.txtLatitude);
        longitude = (TextView) activity.findViewById(R.id.txtLongitude);
        updatedTime = (TextView) activity.findViewById(R.id.txt_updated_time);
    }

    @Test
    public void activityNotNull() throws Exception {
        assertNotNull(activity);
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
