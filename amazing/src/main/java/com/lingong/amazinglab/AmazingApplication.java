package com.lingong.amazinglab;

import android.app.Application;
import android.content.Context;

/**
 * Author:Miracle.lin
 * Date:2021/5/10 10:34
 * Descri:
 */
public class AmazingApplication extends Application {
    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
//        RxRetrofitApp.init(this, BuildConfig.DEBUG);
    }

}
