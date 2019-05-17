package com.example.lurenman.threaddemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 创建日期：2019/5/17
 * 作者:baiyang
 * 具体还是看源码可以理解
 */
public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String name = bundle.getString("name", "");
            String age = bundle.getString("age");
            Log.e(TAG, "onHandleIntent: name:" + name);
            Log.e(TAG, "onHandleIntent: age:" + age);
        }
        //此时的线程不是主线程，因为intentService的有在本 线程创建的handler，hangdler回调的onHandleIntent
        String threadName = Thread.currentThread().getName();
        Log.e(TAG, "onHandleIntent: thread name:" + threadName);
    }
}
