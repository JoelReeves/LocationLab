package com.bromancelabs.locationlab.util;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.Snackbar;

import com.bromancelabs.locationlab.R;

public final class SnackBarHelper {

    private SnackBarHelper() {}

    private static void getSnackBar(Activity activity, int text, int textColor, int backgroundColor) {
        final Snackbar snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(textColor);
        snackbar.getView().setBackgroundColor(backgroundColor);
        snackbar.show();
    }

    /**
     *
     * @param activity
     *          current activity
     */
    public static void networkUnavailable(Activity activity) {
        getSnackBar(activity, R.string.network_unavailable, Color.WHITE, Color.RED);
    }

    /**
     *
     * @param activity
     *          current activity
     */
    public static void mapUnavailable(Activity activity) {
        getSnackBar(activity, R.string.maps_unavailable, Color.WHITE, Color.RED);
    }

    /**
     *
     * @param activity
     *          current activity
     */
    public static void locationUnavailable(Activity activity) {
        getSnackBar(activity, R.string.location_unavailable, Color.WHITE, Color.RED);
    }

}
