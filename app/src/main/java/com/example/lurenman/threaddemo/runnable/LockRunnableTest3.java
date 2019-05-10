package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建日期：2019/5/10
 * 作者:baiyang
 * tryLock()测试
 * lock.tryLock(1, TimeUnit.SECONDS) 这个说白了就是先尝试的获取锁，如果锁被占用就等待1秒
 * 再次 尝试获取锁
 */
public class LockRunnableTest3 implements Runnable {
    private static final String TAG = "LockRunnableTest3";
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
//        if (lock.tryLock()) {
//            Log.e(TAG, Thread.currentThread().getName() + "获取到了锁");
//        }else {
//            Log.e(TAG, Thread.currentThread().getName() + "获取锁失败");
//        }
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                Log.e(TAG, Thread.currentThread().getName() + "获取到了锁");
            } else {
                Log.e(TAG, Thread.currentThread().getName() + "获取锁失败");
            }
            //通过下面两个时间可以做一个测试
            // Thread.sleep(2000);
            // Thread.sleep(500);
        } catch (InterruptedException e) {
            Log.e(TAG, "run: " + e.getMessage());
            e.printStackTrace();
        } finally {
            //这块把锁接触之后就lock.tryLock就会立马获取到索而不用在等待一秒去获取锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                Log.e(TAG, Thread.currentThread().getName() + "解除锁");
            }
        }

    }
}
