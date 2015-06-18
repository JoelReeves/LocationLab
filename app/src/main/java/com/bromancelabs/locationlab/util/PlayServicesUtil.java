package com.bromancelabs.locationlab.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public final class PlayServicesUtil {

    private PlayServicesUtil() {}

    public static GoogleApiAvailability getGoogleApiInstance() {
        return GoogleApiAvailability.getInstance();
    }

    public static boolean isGooglePlayAvailable(Activity activity) {

        final int status = getGoogleApiInstance().isGooglePlayServicesAvailable(activity);

        if (status == ConnectionResult.SUCCESS) {
            return true;
        } else {
            Dialog dialog = getGoogleApiInstance().getErrorDialog(activity, status, 0);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    dialog.cancel();
                }
            });
        }
        return false;
    }
}
