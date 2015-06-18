package com.bromancelabs.locationlab;

import android.app.Application;

public class BaseApplication extends Application {

    private static BaseApplication instance = new BaseApplication();

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
