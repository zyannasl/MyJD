package com.example.lenovo.myjingdong;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class ContextApplication extends Application {
    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
    public static Context getAppContext(){
        return context;
    }
}
