package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建日期：2019/5/10
 * 作者:baiyang
 * Condition 测试
 * Condition 的接口方法 必须在同步代码中执行否则抛出 java.lang.IllegalMonitorStateException
 * 所以我们要获取到锁
 */
public class LockRunnableTest4 implements Runnable {
    private static final String TAG = "LockRunnableTest4";
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            Log.e(TAG, "run: " + Thread.currentThread().getName() + "线程开始等待");
            condition.await();
            Log.e(TAG, "run: " + Thread.currentThread().getName() + "等待结束开始执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public Condition getCondition() {
        return condition;
    }
}
