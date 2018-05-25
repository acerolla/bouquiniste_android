package com.acerolla.bouquiniste;

import android.app.Application;

import com.acerolla.bouquiniste.data.utils.BackgroundThread;
import com.acerolla.bouquiniste.data.utils.cloud.ApiManager;
import com.acerolla.bouquiniste.data.utils.db.DbHelper;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.utils.AppCloseDetector;
import com.acerolla.bouquiniste.presentation.utils.Logger;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class BouquinisteApplication extends Application {

    private static BouquinisteApplication sInstance;

    public static BouquinisteApplication getInstance() {
        return sInstance;
    }

    private DbHelper mDbHelper;
    private ApiManager mApiManager;
    private BackgroundThread mBackgroundThread;

    private AppCloseDetector mCloseDetector;


    @Override
    public void onCreate() {
        super.onCreate();

        Logger.enableLogIfDebug();

        sInstance = this;
        mCloseDetector = new AppCloseDetector();
    }

    public DbHelper getDbHelper() {
        if (mDbHelper == null) {
            mDbHelper = new DbHelper(this);
        }

        return mDbHelper;
    }

    public ApiManager getApiManager() {
        if (mApiManager == null) {
            mApiManager = new ApiManager();
        }
        return mApiManager;
    }

    public final BackgroundThread getBackgroundThread() {
        if (mBackgroundThread == null) {
            mBackgroundThread = new BackgroundThread();
        }

        return mBackgroundThread;
    }

    public void release() {
        mDbHelper = null;

        if (mBackgroundThread != null) {
            mBackgroundThread.release();
        }
        mBackgroundThread = null;

        if (mApiManager != null) {
            mApiManager.release();
        }
        mApiManager = null;

        if (mCloseDetector != null) {
            mCloseDetector.release();
        }
        mCloseDetector = null;

        DiManager.release();
    }

}
