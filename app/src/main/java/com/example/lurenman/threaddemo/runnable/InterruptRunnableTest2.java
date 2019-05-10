package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

/**
 * 创建日期：2019/5/9
 * 作者:baiyang
 * 当线程处于打断状态的时候Thread.currentThread().isInterrupted()
 * 为true我们跑出异常通过捕获做相应的操作 终止了线程
 */
public class InterruptRunnableTest2 implements Runnable {
    private static final String TAG = "InterruptRunnableTest1";

    @Override
    public void run() {
        try {
            if (Thread.currentThread().isInterrupted()) {
                Log.e(TAG, "run:" + "线程是打断状态");
                throw new InterruptedException();
            }
            Log.e(TAG, "线程name: " + Thread.currentThread().getName());//由于上面抛异常这块就执行不到了
        } catch (InterruptedException e) {
            Log.e(TAG, "线程:" + Thread.currentThread().getName()+"异常被捕获");
            e.printStackTrace();
        }

    }
}
