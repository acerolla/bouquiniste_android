package com.acerolla.bouquiniste.presentation.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.acerolla.bouquiniste.BouquinisteApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AppCloseDetector {

    private static final int ZERO = 0;

    private static final String TAG = AppCloseDetector.class.getSimpleName();

    private List<String> mRunningActivities;

    public AppCloseDetector() {
        mRunningActivities = Collections.synchronizedList(new ArrayList<String>());
        BouquinisteApplication.getInstance().registerActivityLifecycleCallbacks(mCallbacks);
    }

    private Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            trackActivityCreate(activity.getClass().getName());
        }

        @Override
        public void onActivityStarted(Activity activity) {
            // ignore
        }

        @Override
        public void onActivityResumed(Activity activity) {
            // ignore
        }

        @Override
        public void onActivityPaused(Activity activity) {
            // ignore
        }

        @Override
        public void onActivityStopped(Activity activity) {
            // ignore
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            // ignore
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            trackActivityDestroy(activity.getClass().getName());
            tryCloseApp();
        }
    };

    private void trackActivityCreate(final String activityName) {
        if (mRunningActivities != null) {
            mRunningActivities.add(activityName);
            logList();
        }
    }

    private void logList() {
        if (mRunningActivities != null) {
            Log.e(TAG, "logging list of activities .....");
            if (mRunningActivities.size() == ZERO) {
                Log.e(TAG, "no running activities was found");
                return;
            }

            for (String name: mRunningActivities) {
                Log.e(TAG,name + " is running");
            }
        }
    }

    private void trackActivityDestroy(final String activityName) {
        if (mRunningActivities != null) {
            mRunningActivities.remove(activityName);
            logList();
        }
    }

    private void tryCloseApp() {
        if (!isAppRunning()) {
            BouquinisteApplication.getInstance().release();
        }
    }

    private boolean isAppRunning() {
        return mRunningActivities != null && !mRunningActivities.isEmpty();
    }

    public final void release() {
        BouquinisteApplication.getInstance().unregisterActivityLifecycleCallbacks(mCallbacks);
        mCallbacks = null;
    }
}
