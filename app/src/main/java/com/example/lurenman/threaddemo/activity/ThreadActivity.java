package com.example.lurenman.threaddemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;

/**
 * @author: baiyang.
 * Created on 2017/11/7.
 * 有三种方法可以结束线程：
 * <p>
 * 1. 使用退出标志，使线程正常退出，也就是当run方法完成后线程终止
 * <p>
 * 2. 使用interrupt()方法中断线程
 * <p>
 * 3. 使用stop方法强行终止线程（不推荐使用，可能发生不可预料的结果）
 * http://www.cnblogs.com/l2rf/p/5566895.html
 */

public class ThreadActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "ThreadActivity";
    private Button bt_click;
    private Button bt_Stop;
    private Thread mThread;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        bt_click = (Button) findViewById(R.id.bt_click);
        bt_Stop = (Button) findViewById(R.id.bt_Stop);

        bt_click.setOnClickListener(this);
        bt_Stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == bt_click) {
            //下面线程属于并发执行
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        Log.e(TAG, "thread1执行完了");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        Log.e(TAG, "thread2执行完了");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        Log.e(TAG, "thread3执行完了");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        //停止线程测试
        if (view == bt_Stop) {
            Log.e(TAG, "-------");
            mThread = new Thread(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        Log.e(TAG, "thread开始了");
//                        Thread.sleep(5000);
//                        Log.e(TAG, "thread执行完了");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        Log.e(TAG, "Exception :"+e.getMessage());
//                    }
                    //线程里开线程
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(2000);
                            Log.e(TAG, "thread-----开始了");
                        }
                    }).start();
                    Log.e(TAG, "thread开始了");
                    SystemClock.sleep(5000);
                    Log.e(TAG, "thread执行完了");

                }
            });
            mThread.start();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // mThread.interrupt();//打断线程,这个打断方法只有捕获异常的时候才可以你懂得
    }
}
