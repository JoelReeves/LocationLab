package com.bromancelabs.locationlab.support;

import android.app.Application;
import android.graphics.drawable.Drawable;

import org.robolectric.RuntimeEnvironment;

public class ResourceLocator {

    public static Application getApplication() {
        return RuntimeEnvironment.application;
    }

    public static String getString(int stringId) {
        return getApplication().getString(stringId);
    }

    public static Drawable getDrawable(int drawableId) {
        return getApplication().getResources().getDrawable(drawableId);
    }

    public static String[] getStringArray(int arrayId) {
        return getApplication().getResources().getStringArray(arrayId);
    }
}
