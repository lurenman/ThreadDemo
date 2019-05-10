package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

/**
 * 创建日期：2019/5/10
 * 作者:baiyang
 * Join测试
 */
public class JoinRunnable implements Runnable {
    private static final String TAG = "JoinRunnable";

    @Override
    public void run() {
        try {
            Log.e(TAG, Thread.currentThread().getName() + "运行开始");
            Thread.sleep(2000);
            Log.e(TAG, Thread.currentThread().getName() + "运行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
