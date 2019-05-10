package com.example.lurenman.threaddemo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;

/**
 * 创建日期：2019/5/8
 * 作者:baiyang
 */
public class LockActivity extends BaseNewActivity {

    private Button btn_reentrant_lock;
    private Button btn_interrupt;

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
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initListenter() {
        btn_reentrant_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LockActivity.this, ReentrantLockActivity.class);
                startActivity(intent);
            }
        });
        btn_interrupt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LockActivity.this, InterruptActivity.class);
                startActivity(intent);
            }
        });
    }
}
