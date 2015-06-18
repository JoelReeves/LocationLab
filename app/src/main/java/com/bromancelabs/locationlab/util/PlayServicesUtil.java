package com.bromancelabs.locationlab.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;

import com.google.android.gms.common.GoogleApiAvailability;

public final class PlayServicesUtil {

    private PlayServicesUtil() {}

    /**
     *
     * @return
     *          returns the Google API Availability instance
     */
    public static GoogleApiAvailability getGoogleApiInstance() {
        return GoogleApiAvailability.getInstance();
    }

    /**
     *
     * @param activity
     *          current activity
     * @param statusCode
     *          status code to pass to the Google Play error dialog
     */
    public static void displayGoogleErrorDialog(Activity activity, int statusCode) {
        Dialog dialog = getGoogleApiInstance().getErrorDialog(activity, statusCode, 0);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.cancel();
            }
        });
    }
}
