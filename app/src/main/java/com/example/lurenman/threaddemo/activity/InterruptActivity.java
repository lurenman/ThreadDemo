package com.example.lurenman.threaddemo.activity;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;
import com.example.lurenman.threaddemo.runnable.InterruptRunnableTest1;
import com.example.lurenman.threaddemo.runnable.InterruptRunnableTest2;
import com.example.lurenman.threaddemo.runnable.InterruptRunnableTest3;

/**
 * 创建日期：2019/5/9
 * 作者:baiyang
 */
public class InterruptActivity extends BaseNewActivity {
    private static final String TAG = "InterruptActivity";
    private Button btn_test;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "线程中断测试";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_interrupt;
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
                //test1();
                //test2();
                test3();
            }
        });
    }

    /**
     * isInterrupted()这个方法就是判断标志位是不是打断的，当线程是阻塞状态
     * 之后 这个标志位会复位返回false
     */
    private void test1() {
        InterruptRunnableTest1 interruptRunnableTest1 = new InterruptRunnableTest1();
        Thread thread = new Thread(interruptRunnableTest1);
        thread.start();
        Log.e(TAG, "执行1打断状态" + thread.isInterrupted());//false
        //这个执行把打断状态置位true
        thread.interrupt();
        Log.e(TAG, "执行2打断状态" + thread.isInterrupted());//true
        try {
            Log.e(TAG, "执行3打断状态" + thread.isInterrupted());//true
            thread.sleep(1000);
            //Thread.sleep(1000);
        } catch (InterruptedException e) {
            Log.e(TAG, "test1: " + e.getMessage());//这块没有执行到
            e.printStackTrace();
        } finally {
            Log.e(TAG, "执行4打断状态" + thread.isInterrupted());//false
        }
    }

    private void test2() {
        InterruptRunnableTest2 interruptRunnableTest2 = new InterruptRunnableTest2();
        Thread thread = new Thread(interruptRunnableTest2);
        thread.start();
        Log.e(TAG, "执行1打断状态" + thread.isInterrupted());//false
        //这个执行把打断状态置位true
        thread.interrupt();
        Log.e(TAG, "执行2打断状态" + thread.isInterrupted());//true
        try {
            Log.e(TAG, "执行3打断状态" + thread.isInterrupted());//true
            thread.sleep(1000);
            //Thread.sleep(1000);
        } catch (InterruptedException e) {
            Log.e(TAG, "test2: " + e.getMessage());//这块没有执行到
            e.printStackTrace();
        } finally {
            Log.e(TAG, "执行4打断状态" + thread.isInterrupted());//false
        }
    }

    private void test3() {
        InterruptRunnableTest3 interruptRunnableTest3 = new InterruptRunnableTest3();
        Thread thread = new Thread(interruptRunnableTest3);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
