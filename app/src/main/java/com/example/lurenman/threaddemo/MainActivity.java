package com.example.lurenman.threaddemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.lurenman.threaddemo.activity.AsyncTaskActivity;
import com.example.lurenman.threaddemo.activity.CallableTestActivity;
import com.example.lurenman.threaddemo.activity.ScheduledThreadPoolLActivity;
import com.example.lurenman.threaddemo.activity.ThreadActivity;
import com.example.lurenman.threaddemo.activity.ThreadPoolExecutorActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tv_ScheduledThreadPool;
    private TextView tv_CallableTest;
    private TextView tv_AsyncTask;
    private TextView tv_thread;
    private TextView tv_ThreadPoolExecutor;
    private TextView tv_main1;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        tv_ScheduledThreadPool = (TextView) findViewById(R.id.tv_ScheduledThreadPool);
        tv_CallableTest = (TextView) findViewById(R.id.tv_CallableTest);
        tv_AsyncTask = (TextView) findViewById(R.id.tv_AsyncTask);
        tv_thread = (TextView) findViewById(R.id.tv_thread);
        tv_ThreadPoolExecutor = (TextView) findViewById(R.id.tv_ThreadPoolExecutor);
        tv_main1 = (TextView) findViewById(R.id.tv_main1);
        initEvents();
    }

    private void initEvents() {
        tv_ScheduledThreadPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScheduledThreadPoolLActivity.class);
                startActivity(intent);
            }
        });
        tv_CallableTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CallableTestActivity.class);
                startActivity(intent);
            }
        });
        tv_AsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                startActivity(intent);
            }
        });
        tv_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThreadActivity.class);
                startActivity(intent);
            }
        });
        tv_ThreadPoolExecutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThreadPoolExecutorActivity.class);
                startActivity(intent);
            }
        });
        tv_main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity1.class);
                startActivity(intent);
            }
        });
    }
}
