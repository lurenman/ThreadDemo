package com.example.lurenman.threaddemo.runnable;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

/**
 * 创建日期：2019/5/9
 * 作者:baiyang
 * 之前test2 中可以自己抛异常处理，在本test3 中通过sleep(1000);
 * 本线程每一秒处于阻塞状态，当线程正处于阻塞状态时候 调用interrupt()
 * 会捕获到InterruptedException 线程中断，这样我们这样就可以搞事情
 */
public class InterruptRunnableTest3 implements Runnable {
    private static final String TAG = "InterruptRunnableTest3";

    @Override
    public void run() {
        while (true) {
            try {
                SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                Log.e(TAG, sim.format(new Date()));
                sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "run: 线程中断");
                return;
            }
        }


    }
}
