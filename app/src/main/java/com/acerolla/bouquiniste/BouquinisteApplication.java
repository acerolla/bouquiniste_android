package com.acerolla.bouquiniste;

import android.app.Application;

import com.acerolla.bouquiniste.data.utils.BackgroundThread;
import com.acerolla.bouquiniste.data.utils.db.DbHelper;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class BouquinisteApplication extends Application {

    private static BouquinisteApplication sInstance;

    public static BouquinisteApplication getInstance() {
        return sInstance;
    }

    private DbHelper mDbHelper;
    private BackgroundThread mBackgroundThread;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public DbHelper getDbHelper() {
        if (mDbHelper == null) {
            mDbHelper = new DbHelper(this);
        }

        return mDbHelper;
    }

    public final BackgroundThread getBackgroundThread() {
        if (mBackgroundThread == null) {
            mBackgroundThread = new BackgroundThread();
        }

        return mBackgroundThread;
    }

}
