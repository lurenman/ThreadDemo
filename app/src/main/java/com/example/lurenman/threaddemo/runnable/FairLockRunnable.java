package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建日期：2019/5/9
 * 作者:baiyang
 * 公平锁测试
 */
public class FairLockRunnable implements Runnable {
    private static final String TAG = "FairLockRunnable";
    private ReentrantLock reentrantLock;
    //当前拥有的票数
    private int num = 100;

    public FairLockRunnable(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                reentrantLock.lock();
                if (num > 0) {
                    Thread.sleep(10);
                    //输出卖票信息
                    Log.e(TAG, Thread.currentThread().getName()+"获取到了锁" + ".....sale...." + num--);
                }
            } catch (InterruptedException e) {
                //出现异常就中断
                Thread.currentThread().interrupt();
            } finally {
                //锁的释放一定要在finally，如果前面崩了，这块没有释放锁，后面就获取不到锁就gg
                reentrantLock.unlock();
            }

        }
    }
}
