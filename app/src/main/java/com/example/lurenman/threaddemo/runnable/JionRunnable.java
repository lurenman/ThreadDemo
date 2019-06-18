package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

/**
 * 创建日期：2019/6/18
 * 作者:baiyang
 */
public class JionRunnable implements Runnable {
    private static final String TAG = "JionRunnable";


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Log.e(TAG, "thread name: "+Thread.currentThread().getName());
        }
    }
}
