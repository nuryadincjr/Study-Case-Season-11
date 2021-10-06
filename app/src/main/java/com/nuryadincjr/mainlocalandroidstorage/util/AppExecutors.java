package com.nuryadincjr.mainlocalandroidstorage.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import android.os.Handler;
import android.os.Looper;

public class AppExecutors {
    private static final Object LOCK = new Object();
    private static AppExecutors sIntance;
    private final Executor diskID;
    private final Executor mainThread;
    private final Executor networkThread;

    public AppExecutors(Executor diskID, Executor mainThread, Executor networkThread) {
        this.diskID = diskID;
        this.mainThread = mainThread;
        this.networkThread = networkThread;
    }

    public static AppExecutors getsIntance() {
        if(sIntance == null) {
            synchronized (LOCK) {
                sIntance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sIntance;
    }

    public Executor diskID() {
        return diskID;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor networkID() {
        return networkThread;
    }

    public static class MainThreadExecutor implements Executor {
        private Handler manThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable runnable) {
            manThreadHandler.post(runnable);
        }

    }

}
