package com.example.lurenman.threaddemo.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;

/**
 * 创建日期：2019/5/16
 * 作者:baiyang
 * 看源码分析
 * 参考https://blog.csdn.net/lmj623565791/article/details/38377229
 * https://blog.csdn.net/cc_lova_wxf/article/details/73250612
 */
public class HandlerActivity extends BaseNewActivity {
    private static final String TAG = "HandlerActivity";
    private Button btn_test;
    @SuppressLint("HandlerLeak")
    //看源码传不传callback 会回调handleMessage
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    Log.e(TAG, "handleMessage: 100");
                    break;
            }
            super.handleMessage(msg);
        }
    };
    //    private Handler h1=new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            return false;
//        }
//    });

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "Handler";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_handler;
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
                // test1();
                //test2();
                test3();
            }
        });
    }

    private void test1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Message message = new Message();
                //重message 池中 拿
                Message message = Message.obtain();
                message.what = 100;
                mHandler.sendMessage(message);
                // mHandler.handleMessage(message);
                //mHandler.dispatchMessage(message);
            }
        });
        thread.start();
    }

    private void test2() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //死循环
                for (; ; ) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        //捕获到异常之后 跳出结束死循环
                        break;
                    }
                    Log.e(TAG, "test2: -------------");
                }
            }
        });
        thread.start();
        try {
            //三秒后打断
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    /**
     * 看源码知道   m.callback = r;把当前runnable 赋值到m.callback，在也是调用 enqueueMessage
     * 之后和上面的一样
     */
    private void test3() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: test3()-----1");
            }
        });
        //mHandler.postAtTime()
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: test3()-----2");
            }
        }, 2000);
        //SystemClock.uptimeMillis()这个指的的是开始时间
        //源码调用sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayMillis);

    }
}
