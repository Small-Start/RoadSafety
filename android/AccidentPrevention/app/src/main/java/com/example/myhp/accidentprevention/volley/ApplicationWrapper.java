package com.example.myhp.accidentprevention.volley;

import android.app.Application;
import android.content.Context;


public class ApplicationWrapper extends Application {
    private static ApplicationWrapper applicationWrapper;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationWrapper = this;
        super.onCreate();
    }

    public static ApplicationWrapper getApplicationWrapper() {
        return applicationWrapper;
    }

    public static Context getAppContext() {

        return applicationWrapper.getApplicationContext();

    }

}
