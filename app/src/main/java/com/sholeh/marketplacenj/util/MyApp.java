package com.sholeh.marketplacenj.util;

import android.app.Application;
import android.content.Context;
import android.util.Log;

//5.5 16:27
public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    public static Context getContext(){
        return context;
    }
}
