package com.bromancelabs.locationlab.support;

import android.view.View;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class Assert {

    public static void assertViewIsVisible(View view) {
        assertNotNull(view);
        assertThat(view.getVisibility(), equalTo(View.VISIBLE));
    }

    public static void assertViewIsGone(View view) {
        assertNotNull(view);
        assertThat(view.getVisibility(), equalTo(View.GONE));
    }
}
