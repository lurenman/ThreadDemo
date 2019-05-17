package com.example.lurenman.threaddemo.activity;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;

/**
 * 创建日期：2019/5/16
 * 作者:baiyang
 * 参考https://blog.csdn.net/javazejian/article/details/52426353
 * 通过看源码HandlerThread 主要也就是个thread 只是为我们封装了 looper，及提供了两个quit()，quitSafely()方法
 * todo 写成博客
 */
public class HandlerThreadActivity extends BaseNewActivity {

    private Button btn_test;
    private static final String TAG = "HandlerThreadActivity";
    private Handler mHandler;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "HandlerThread";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_common;
    }

    @Override
    protected void initView() {
        btn_test = (Button) findViewById(R.id.btn_test);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initListenter() {
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  test1();
                // test2();
                test3();
            }
        });
    }

    private void test1() {
        HandlerThread handlerThread = new HandlerThread("handler-thread1");
        handlerThread.start();
        //通过源码可知 得到的looper是当前线程looper
        Looper looper = handlerThread.getLooper();
        //通过当前looper创建Handler这样才能操控当前loop、messagequeue这样才能操控统一消息，由于HandlerThread没有提供添加Runnable接口，
        //通过当前looper创建Handler
        final Handler handler = new Handler(looper, new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.e(TAG, "test1(): " + msg.obj);
                return false;
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message obtain = Message.obtain();
                obtain.what = 100;
                obtain.obj = "test1";
                handler.sendMessage(obtain);
            }
        }, 1000);

    }

    private void test2() {
        HandlerThread handlerThread = new HandlerThread("handler-thread2") {
            @Override
            public void run() {
                Log.e(TAG, "run: test2------------1");
                super.run();
                //这个log不走
                Log.e(TAG, "run: test2------------2");

            }

            @Override
            protected void onLooperPrepared() {
                Log.e(TAG, "run: test2------------3");
            }
        };
        handlerThread.start();
//        Looper looper = handlerThread.getLooper();
//        Handler handler = new Handler(looper, new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                return false;
//            }
//        });
    }

    /**
     * 还是不要这么在onLooperPrepared()方法里初始化handler，由于下面调用可能为null
     * 在onLooperPrepared()中可以做runnable 的操作
     */
    private void test3() {
        HandlerThread handlerThread = new HandlerThread("handler-thread3") {
            @Override
            protected void onLooperPrepared() {
                mHandler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        Log.e(TAG, "handleMessage:----------test3() ");
                        return false;
                    }
                });
                //这种就和之前在线程 run中发送消息是一样的
                mHandler.sendMessage(Message.obtain());
            }
        };
        handlerThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendMessage(new Message());
            }
        }, 1000);
    }
}
