package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

/**
 * 创建日期：2019/6/18
 * 作者:baiyang
 */
public class YieldRunnable implements Runnable {
    private static final String TAG = "YieldRunnable";

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Thread.yield();
            Log.e(TAG, "run: thread name " + Thread.currentThread().getName());
        }
    }
}
