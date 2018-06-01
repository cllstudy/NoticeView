package com.cllstudy.androidclassutils.utils;

import android.os.Handler;


/**
 * Created by hang on 2017/6/8
 * 1. 在子线程中执行
 * 2.  在主线程执行
 */
public class ThreadUtil {

    /**
     * 在子线程执行的方法
     *
     * @param runnable
     */
   public static void runOnBackThread(Runnable runnable) {
        ThreadManager.getThreadPollProxy().execute(runnable);
    }

    private static Handler handler = new Handler();

    /**
     * 主线程执行
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }
}
