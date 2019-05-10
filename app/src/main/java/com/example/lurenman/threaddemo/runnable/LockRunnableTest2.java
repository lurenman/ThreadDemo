package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建日期：2019/5/10
 * 作者:baiyang
 * 这个例子通过中断解决死锁问题
 */
public class LockRunnableTest2 implements Runnable {
    private static final String TAG = "LockRunnableTest2";
    private ReentrantLock lock1 = new ReentrantLock();
    private ReentrantLock lock2 = new ReentrantLock();
    private LockType lockType;

    public LockRunnableTest2(LockType type) {
        lockType = type;
    }

    @Override
    public void run() {
        try {
            if (lockType == LockType.TYPE1) {
                try {
                    Log.e(TAG, "log1-------------------------- ");
                    lock1.lockInterruptibly();
                    Log.e(TAG, "log2-------------------------- ");
                    Thread.sleep(500);
                    Log.e(TAG, "log3-------------------------- ");
                    lock2.lockInterruptibly();
                    Log.e(TAG, "log4-------------------------- ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Log.e(TAG, "log5-------------------------- ");
                    lock2.lockInterruptibly();
                    Log.e(TAG, "log6-------------------------- ");
                    Thread.sleep(500);
                    Log.e(TAG, "log7-------------------------- ");
                    lock1.lockInterruptibly();
                    Log.e(TAG, "log8-------------------------- ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //作用是查询当前线程是否保持此锁定
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
                Log.e(TAG, "log9-------------------------- ");
            }

            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
                Log.e(TAG, "log10-------------------------- ");
            }
            Log.e(TAG, "log11-------------------------- ");
        }

    }

    public enum LockType {
        TYPE1, TYPE2
    }
}
