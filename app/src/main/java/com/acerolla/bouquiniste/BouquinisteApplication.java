package com.acerolla.bouquiniste;

import android.app.Application;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.05.2018.
 */
public class BouquinisteApplication extends Application {

    public static BouquinisteApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public static BouquinisteApplication getInstance() {
        return sInstance;
    }
}
