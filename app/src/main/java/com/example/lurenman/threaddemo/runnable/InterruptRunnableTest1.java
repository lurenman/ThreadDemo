package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

/**
 * 创建日期：2019/5/9
 * 作者:baiyang
 */
public class InterruptRunnableTest1 implements Runnable {
    private static final String TAG = "InterruptRunnableTest1";

    @Override
    public void run() {
        if (Thread.currentThread().isInterrupted()) {
            Log.e(TAG,"run:"+"线程是打断状态");
        }
        Log.e(TAG, "线程name: " + Thread.currentThread().getName());
    }
}
