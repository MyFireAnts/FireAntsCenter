package com.example.ants.fireantscenteri;

import android.app.Application;

/**
 * Created by 15291 on 2016/10/19.
 */

public class FuLiCenterApplication extends Application {

    private static FuLiCenterApplication instance;
    public static FuLiCenterApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        application = this;
    }

    public static FuLiCenterApplication getInstance() {
        if (instance != null) {
            instance = new FuLiCenterApplication();
        }
        return instance;
    }
}
