package com.example.lurenman.threaddemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lurenman.threaddemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: baiyang.
 * http://blog.csdn.net/u010687392/article/details/49850803 好文章
 * http://blog.csdn.net/javazejian/article/details/50890554 好文章
 * Created on 2017/11/6.
 * scheduleAtFixedRate ，是以上一个任务开始的时间计时，period时间过去后，检测上一个任务是否执行完毕，
 * 如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行。
 * scheduleWithFixedDelay，是以上一个任务结束时开始计时，period时间过去后，立即执行。
 * 重点：
 * 两个方法以不同的时间点作为参考。
 */

public class ScheduledThreadPoolLActivity extends Activity {
    private static final String TAG = "ScheduledThreadPoolLAct";
    private Button bt_click;
    private boolean isClick1 = false;
    private boolean isClick2 = false;
    private Button bt_scheduleAtFixedRate;
    private ScheduledExecutorService scheduledThreadPool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduledthreadpool);
        bt_click = (Button) findViewById(R.id.bt_click);
        bt_scheduleAtFixedRate = (Button) findViewById(R.id.bt_scheduleAtFixedRate);
        initEvents();
    }

    private void initEvents() {
        bt_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClick1) {
                    isClick1 = true;
                    //   ScheduledThreadPoolExecutor se = new ScheduledThreadPoolExecutor(5);
                    scheduledThreadPool = Executors.newScheduledThreadPool(5);
                    try {
                        //schedule to run after sometime
                        // System.out.println("Current Time = "+getNowDate());
                        Log.e(TAG, "Current Time = " + getNowDate());
                        for (int i = 0; i < 3; i++) {
                            Thread.sleep(1000);//这个可以控制线程执行间隔的，也可以不设置（三个线程同时开启结束）
                            WorkerThread worker = new WorkerThread();
                            //延迟2秒后执行
                            scheduledThreadPool.schedule(worker, 2, TimeUnit.SECONDS);

                        }
                        //  Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //这个结束代码不写下面的log不打
                    scheduledThreadPool.shutdown();

                    while (!scheduledThreadPool.isTerminated()) {
                        //wait for all tasks to finish
                    }
                    //System.out.println("Finished all threads");
                    Log.e(TAG, "Finished all threads" + getNowDate());
                } else {
                    Toast.makeText(getApplicationContext(), "button1已经点击了", Toast.LENGTH_SHORT).show();
                }
            }

        });
        bt_scheduleAtFixedRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClick2) {
                    isClick2 = true;
                    ScheduledThreadPoolExecutor se = new ScheduledThreadPoolExecutor(2);
                    // 设定可以循环执行的runnable,初始延迟为0，这里设置的任务的间隔为2秒
                    for (int i = 0; i < 2; i++) {
                        //这个一直循环执行我。。。
                        se.scheduleAtFixedRate(new FixedSchedule(), 0, 2, TimeUnit.SECONDS);
                        // se.scheduleWithFixedDelay(new FixedSchedule(), 0, 2, TimeUnit.SECONDS);
                    }
                    //当任务没有终止的时候
                    while (!se.isTerminated()) {
                        //wait for all tasks to finish
                        // Log.e(TAG,"------------------------------------------");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "button2已经点击了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class WorkerThread implements Runnable {
        @Override
        public void run() {
            // System.out.println(Thread.currentThread().getName() + " Start. Time = " + getNowDate());
            Log.e(TAG, Thread.currentThread().getName() + " Start. Time = " + getNowDate());
            threadSleep();
            System.out.println(Thread.currentThread().getName() + " End. Time = " + getNowDate());
            Log.e(TAG, Thread.currentThread().getName() + " End. Time = " + getNowDate());

        }

        /**
         * 睡3秒
         */
        public void threadSleep() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public class FixedSchedule implements Runnable {
        @Override
        public void run() {
            Log.e(TAG, "当前线程：" + Thread.currentThread().getName() + "  当前时间：" + getNowDate());
        }
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ctime = formatter.format(currentTime);
        return ctime;
    }
//当线程池执行的时候返回键方法直接被拦截所以。。。待解决
 /*   @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
//            if (null!=scheduledThreadPool) {
//                //这个里等任务完成才可以退出
//                scheduledThreadPool.shutdownNow();
//             return false;
//            }
            boolean shutdown = scheduledThreadPool.isShutdown();
            Log.e(TAG, "onKeyDown: -------"+shutdown );

          if(shutdown)
            {
                Toast.makeText(getApplicationContext(), "线程池任务结束", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return false;
        //return super.onKeyDown(keyCode, event);
    }*/

    @Override
    protected void onDestroy() {
        if (scheduledThreadPool != null)
            scheduledThreadPool.shutdownNow();
        super.onDestroy();
    }
}
