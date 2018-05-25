package com.acerolla.bouquiniste.presentation.utils;

import android.util.Log;

import com.acerolla.bouquiniste.BuildConfig;
import com.j256.ormlite.logger.LocalLog;

import java.util.Date;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class Logger {
    private static final String LOG = "LOG";
    private static boolean isDebugMode = false;

    private static void enableLog() {
        isDebugMode = true;
    }

    public static void i(final String message) {
        if (isDebugMode) {
            final Date date = new Date();
            Log.i(LOG, "[Time is === " + date.toString() + "] " + message);
        }
    }

    public static void e(final String message) {
        if (isDebugMode) {
            Log.e(LOG, message);
        }
    }

    public static void d(final String message) {
        if (isDebugMode) {
            Log.d(LOG, message);
        }
    }

    public static void w(final String message) {
        if (isDebugMode) {
            Log.w(LOG, message);
        }
    }

    public static void v(final String message) {
        if (isDebugMode) {
            Log.v(LOG, message);
        }
    }

    public static void printStackTrace(final Exception e) {
        if (isDebugMode) {
            e.printStackTrace();
        }
    }

    public static void enableLogIfDebug() {
        if (isDebugMode()) {
            enableLog();
        } else {
            // hack to prevent logs from ormlite
            System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
        }

        Logger.e("debug? " + isDebugMode);
    }

    private static boolean isDebugMode() {
        boolean debuggable = false;

        try {
            debuggable = BuildConfig.DEBUG;
        } catch (Throwable e) {
            //debuggable variable will remain false
        }
        return debuggable;
    }

}
