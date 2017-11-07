package com.example.lurenman.threaddemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;

/**
 * @author: baiyang.
 * Created on 2017/11/7.
 */

public class ThreadActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "ThreadActivity";
    private Button bt_click;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        bt_click = (Button) findViewById(R.id.bt_click);

        bt_click.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==bt_click){
        //下面线程属于并发执行
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        Log.e(TAG,"thread1执行完了");
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
                        Log.e(TAG,"thread2执行完了");
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
                        Log.e(TAG,"thread3执行完了");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
