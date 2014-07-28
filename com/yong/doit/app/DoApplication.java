package com.yong.doit.app;

import android.app.Application;

public class DoApplication extends Application {
    private static DoApplication instance;

    public static DoApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
