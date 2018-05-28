package com.acerolla.bouquiniste.data.utils;

import android.os.Handler;
import android.os.Looper;

import com.acerolla.bouquiniste.data.ResultListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public class BackgroundThread {

    private ExecutorService mExecutor;
    private Handler mMainThread;

    public BackgroundThread() {
        mExecutor = Executors.newCachedThreadPool();
        mMainThread = new Handler(Looper.getMainLooper());
    }

    public final void execute(final BouquinisteRunnable runnable, final ResultListener listener) {
        if (runnable != null && mExecutor != null) {
            mExecutor.execute(() -> {
                try {
                    final Object result = runnable.execute();
                    notify(result, listener);
                } catch (Throwable e) {
                    e.printStackTrace();
                    notify(null, listener);
                }
            });
        }
    }

    private final void notify(final Object result, final ResultListener listener) {
        if (mMainThread != null) {
            mMainThread.post(() -> {
                if (listener != null) {
                    listener.onResult(result);
                }
            });
        }
    }

    public final void release() {

        if (mExecutor != null) {
            mExecutor.shutdown();
        }

        mExecutor = null;
        mMainThread = null;
    }
}
