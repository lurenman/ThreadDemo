package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

/**
 * 创建日期：2019/5/8
 * 作者:baiyang
 * 模拟卖票线程问题
 */
public class TicketRunnable implements Runnable{
    private static final String TAG = "TicketRunnable";
    //当前拥有的票数
    private int num = 100;

    @Override
    public void run() {
        while (true) {
            if (num > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                //输出卖票信息
                Log.e(TAG, Thread.currentThread().getName() + ".....sale...." + num--);
            }
        }


    }
}
