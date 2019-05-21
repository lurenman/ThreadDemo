package com.example.lurenman.threaddemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;
import com.example.lurenman.threaddemo.activity.AidlActivity;
import com.example.lurenman.threaddemo.activity.BaseNewActivity;
import com.example.lurenman.threaddemo.activity.FutureTaskActivity;
import com.example.lurenman.threaddemo.activity.HandlerActivity;
import com.example.lurenman.threaddemo.activity.HandlerThreadActivity;
import com.example.lurenman.threaddemo.activity.IntentServiceActivity;
import com.example.lurenman.threaddemo.activity.InterruptActivity;
import com.example.lurenman.threaddemo.activity.ReentrantLockActivity;

/**
 * 创建日期：2019/5/8
 * 作者:baiyang
 */
public class MainActivity1 extends BaseNewActivity {

    private Button btn_reentrant_lock;
    private Button btn_interrupt;
    private Button btn_future_task;
    private Button btn_handler;
    private Button btn_handlerthread;
    private Context mContext;
    private Button btn_IntentService;
    private Button btn_aidl;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "线程锁等测试";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_lock;
    }

    @Override
    protected void initView() {
        btn_reentrant_lock = (Button) findViewById(R.id.btn_reentrant_lock);
        btn_interrupt = (Button) findViewById(R.id.btn_interrupt);
        btn_future_task = (Button) findViewById(R.id.btn_future_task);
        btn_handler = (Button) findViewById(R.id.btn_handler);
        btn_handlerthread = (Button) findViewById(R.id.btn_handlerthread);
        btn_IntentService = (Button) findViewById(R.id.btn_IntentService);
        btn_aidl = (Button) findViewById(R.id.btn_aidl);
    }

    @Override
    protected void initVariables() {
        mContext = this;
    }

    @Override
    protected void initListenter() {
        btn_reentrant_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocalActivity(ReentrantLockActivity.class);
            }
        });
        btn_interrupt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocalActivity(InterruptActivity.class);
            }
        });
        btn_future_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocalActivity(FutureTaskActivity.class);
            }
        });
        btn_handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocalActivity(HandlerActivity.class);
            }
        });
        btn_handlerthread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocalActivity(HandlerThreadActivity.class);
            }
        });
        btn_IntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocalActivity(IntentServiceActivity.class);
            }
        });
        btn_aidl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocalActivity(AidlActivity.class);
            }
        });

    }

    private void startLocalActivity(Class<? extends BaseNewActivity> cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }
}
