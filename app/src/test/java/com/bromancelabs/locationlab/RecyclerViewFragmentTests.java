package com.bromancelabs.locationlab;

import android.support.v7.widget.RecyclerView;

import com.bromancelabs.locationlab.fragments.RecyclerViewFragment;

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
public class RecyclerViewFragmentTests {

    private RecyclerViewFragment fragment;
    private RecyclerView recyclerView;

    @Before
    public void setup() throws Exception {
        fragment = RecyclerViewFragment.newInstance();
        startFragment(fragment);

        recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.recyclerView);
    }

    @Test
    public void fragmentNotNull() throws Exception {
        assertNotNull(fragment);
        assertTrue(fragment instanceof RecyclerViewFragment);
    }

    @Test
    public void recyclerViewNotNull() throws Exception {
        assertViewIsVisible(recyclerView);
    }
}
