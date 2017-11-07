package com.example.lurenman.threaddemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lurenman.threaddemo.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: baiyang.
 * Created on 2017/11/6.
 * 参考
 * http://blog.csdn.net/javazejian/article/details/50896505
 * 这个也就像那个线程回调通知更新那种
 */

public class CallableTestActivity extends Activity {
    private static final String TAG = "CallableTestActivity";
    private Button bt_Callable;
    private Button bt_Runnable;
    private boolean isclick1 = false;
    private boolean isclick2 = false;
    //private boolean isclick3 = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callabletest);
        bt_Callable = (Button) findViewById(R.id.bt_Callable);
        bt_Runnable = (Button) findViewById(R.id.bt_Runnable);
        //FutureTask不展示
//        //创建线程池
//        ExecutorService es = Executors.newSingleThreadExecutor();
//        //创建Callable对象任务
//        CallableDemo calTask=new CallableDemo();
//        //创建FutureTask
//        FutureTask<Integer> futureTask=new FutureTask<>(calTask);
//        //执行任务
//        es.submit(futureTask);
//        //关闭线程池
//        es.shutdown();
        initEvents();
    }

    private void initEvents() {
        bt_Callable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isclick1) {
                    isclick1 = true;
                    //创建线程池
                    ExecutorService es = Executors.newSingleThreadExecutor();
                    //创建Callable对象任务
                    CallableDemo calTask = new CallableDemo();
                    //提交任务并获取执行结果
                    Future<Integer> future = es.submit(calTask);
                    //关闭线程池
                    es.shutdown();
                    try {
                        Thread.sleep(1000);
                        Log.e(TAG, "主线程在执行其他任务");

                        if (future.get() != null) {
                            //输出获取到的结果
                            //System.out.println("future.get()-->"+future.get());
                            Log.e(TAG, "future.get()-->" + future.get());
                            Toast.makeText(getApplicationContext(), "" + future.get(), Toast.LENGTH_SHORT).show();
                        } else {
                            //输出获取到的结果
                            // System.out.println("future.get()未获取到结果");
                            Log.e(TAG, "future.get()未获取到结果");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // System.out.println("主线程在执行完成");
                    Log.e(TAG, "主线程在执行完成");

                } else {
                    Toast.makeText(getApplicationContext(), "button1点击了", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bt_Runnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isclick2) {
                    isclick2 = true;
                    //创建线程池
                    ExecutorService es = Executors.newSingleThreadExecutor();
                    //创建Callable对象任务

                    //提交任务并获取执行结果
                    Future<String> future = (Future<String>) es.submit(new RunnableDemo());
                    //关闭线程池
                    es.shutdown();
                    try {
                        Thread.sleep(1000);
                        Log.e(TAG, "主线程在执行其他任务");

                        if (future.get() != null) {
                            //输出获取到的结果
                            //System.out.println("future.get()-->"+future.get());
                            Log.e(TAG, "future.get()-->" + future.get());
                            Toast.makeText(getApplicationContext(), "" + future.get(), Toast.LENGTH_SHORT).show();
                        } else {
                            //输出获取到的结果
                            // System.out.println("future.get()未获取到结果");
                            Log.e(TAG, "future.get()未获取到结果");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // System.out.println("主线程在执行完成");
                    Log.e(TAG, "主线程在执行完成");


                } else {
                    Toast.makeText(getApplicationContext(), "button2点击了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class CallableDemo implements Callable<Integer> {

        private int sum;

        @Override
        public Integer call() throws Exception {
            //System.out.println("Callable子线程开始计算啦！");
            Log.e(TAG, "Callable子线程开始计算啦！");
            Thread.sleep(1000);

            for (int i = 0; i < 500; i++) {
                sum = sum + i;
            }
            // System.out.println("Callable子线程计算结束！");
            Log.e(TAG, "Callable子线程计算结束！");
            String name = Thread.currentThread().getName();
            Log.e(TAG, "------------"+name);
            return sum;
        }
    }
    public class RunnableDemo implements RunnableFuture<String>
    {
        private String result;

        @Override
        public void run() {
            result="恭喜ssg";
        }

        @Override
        public boolean cancel(boolean b) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return false;
        }

        @Override
        public String get() throws InterruptedException, ExecutionException {

            return result;
        }

        @Override
        public String get(long l, @NonNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return null;
        }
    }

}
