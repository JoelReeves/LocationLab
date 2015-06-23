package com.bromancelabs.locationlab;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bromancelabs.locationlab.fragments.RecyclerViewFragment;
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
public class RecyclerViewFragmentTests {

    private Fragment fragment;
    private TextView recyclerViewheader;
    private RecyclerView recyclerView;

    @Before
    public void setup() throws Exception {
        fragment = RecyclerViewFragment.newInstance();
        startFragment(fragment);

        recyclerView = (RecyclerView) getViewById(R.id.recyclerView);
        recyclerViewheader = (TextView) getViewById(R.id.recyclerviewHeader);
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

    @Test
    public void shouldHaveHeaderText() throws Exception {
        assertViewIsVisible(recyclerViewheader);
        assertEquals(getViewString(recyclerViewheader), ResourceLocator.getString(R.string.recyclerview_header));
    }

    @Test
    public void recyclerViewNotEmpty() throws Exception {
        final int arrayLength = ResourceLocator.getStringArray(R.array.activity_names).length;
        final int recyclerViewSize = recyclerView.getAdapter().getItemCount();
        assertEquals(arrayLength, recyclerViewSize);
    }

    private View getViewById(int id) {
        return fragment.getView().findViewById(id);
    }

    private String getViewString(TextView view) {
        return view.getText().toString();
    }
}
