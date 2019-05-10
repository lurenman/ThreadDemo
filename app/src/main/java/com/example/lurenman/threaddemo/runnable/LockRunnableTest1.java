package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建日期：2019/5/10
 * 作者:baiyang
 * 1、lock.isFair()方法用来判断lock锁是公平锁还是非公平锁。公平锁是指，线程获得锁的顺序是按其等待锁的先后顺序来的，
 * 先来先获得FIFO。反之，非公平锁则是线程随机获得锁的，lock默认是非公平锁。
 * <p>
 * 2、lock.isHeldByCurrentThread()的作用是查询当前线程是否保持此锁定，和lock.hasQueueThread()不同的地方是：
 * lock.hasQueueThread(Thread thread)的作用是判断当前线程是否处于等待lock的状态。
 * <p>
 * 3、lock.isLocked()的作用是查询此锁定是否由任意线程保持。
 * 参考https://blog.csdn.net/XIANZHIXIANZHIXIAN/article/details/86632945
 */
public class LockRunnableTest1 implements Runnable {
    private static final String TAG = "LockRunnableTest1";
    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        try {
            Log.e(TAG, "lock.hasQueueThread(Thread thread): "+ reentrantLock.hasQueuedThread(Thread.currentThread()));//false
            Log.e(TAG, "lock.isLocked(): "+reentrantLock.isLocked());//false
            Log.e(TAG, "lock.isHeldByCurrentThread(): "+reentrantLock.isHeldByCurrentThread());//false
            reentrantLock.lock();
            Log.e(TAG, "lock.isHeldByCurrentThread(): "+reentrantLock.isHeldByCurrentThread());//true
            Log.e(TAG, "lock.isLocked(): "+reentrantLock.isLocked());//true
            Log.e(TAG, "lock.hasQueueThread(Thread thread): "+ reentrantLock.hasQueuedThread(Thread.currentThread()));//false
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }

    }
}
