package com.example.lurenman.threaddemo.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: baiyang.
 * Created on 2017/11/16.
 * 几种Executors的使用
 * 1、newFixedThreadPool()—>LinkedBlockingQueue 无界的队列
 * 2、newSingleThreadExecutor()—>LinkedBlockingQueue
 * 3、newCachedThreadPool()—>SynchronousQueue 直接提交的队列
 * 4、newScheduledThreadPool()—>DelayedWorkQueue 等待队列
 * 5、newSingleThreadScheduledExecutor()—>DelayedWorkQueue
 */

public class ThreadPoolExecutorActivity extends BaseActivity {
    private static final String TAG = "ThreadPoolExecutorActiv";
    private Button bt_newFixedThreadPool;
    private Button bt_newSingleThreadExecutor;
    private Button bt_newCachedThreadPool;
    private Button bt_newScheduledThreadPool;
    private Button bt_newSingleThreadScheduledExecutor;
    private Button bt_Stop;

    private ExecutorService fixedThreadPool;
    private ExecutorService SingleThreadExecutor;
    private ExecutorService CachedThreadPool;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_threadpoolexecutor);
        bt_newFixedThreadPool = (Button) findViewById(R.id.bt_newFixedThreadPool);
        bt_newSingleThreadExecutor = (Button) findViewById(R.id.bt_newSingleThreadExecutor);
        bt_newCachedThreadPool = (Button) findViewById(R.id.bt_newCachedThreadPool);
        bt_newScheduledThreadPool = (Button) findViewById(R.id.bt_newScheduledThreadPool);
        bt_newSingleThreadScheduledExecutor = (Button) findViewById(R.id.bt_newSingleThreadScheduledExecutor);
        bt_Stop = (Button) findViewById(R.id.bt_Stop);

    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
/*        1、newFixedThreadPool() ：
        作用：该方法返回一个固定线程数量的线程池，该线程池中的线程数量始终不变，即不会再创建新的线程，也不会销毁已经创建好的线程，自始自终都是那几个固定的线程在工作，所以该线程池可以控制线程的最大并发数。
        栗子：假如有一个新任务提交时，线程池中如果有空闲的线程则立即使用空闲线程来处理任务，如果没有，则会把这个新任务存在一个任务队列中，一旦有线程空闲了，则按FIFO方式处理任务队列中的任务。*/
        bt_newFixedThreadPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixedThreadPool = Executors.newFixedThreadPool(3);//指定核心
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    fixedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String name = Thread.currentThread().getName();
                            Log.e(TAG, "newFixedThreadPool 线程: " + name + "正在执行第" + index + "个任务");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });
/*        2、newSingleThreadExecutor() ：
        作用：该方法返回一个只有一个线程的线程池，即每次只能执行一个线程任务，多余的任务会保存到一个任务队列中，
        等待这一个线程空闲，当这个线程空闲了再按FIFO方式顺序执行任务队列中的任务。
        newSingleThreadExecutor()和newFixedThreadPool()的方法发现，创建一个singleThreadExecutorPool
        实际上就是创建一个核心线程数和最大线程数都为1的fixedThreadPool。
        */
        bt_newSingleThreadExecutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingleThreadExecutor = Executors.newSingleThreadExecutor();
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    SingleThreadExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            String name = Thread.currentThread().getName();
                            Log.e(TAG, "newSingleThreadExecutor 线程: " + name + "正在执行第" + index + "个任务");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });
/*        3、newCachedThreadPool() ：
        作用：该方法返回一个可以根据实际情况调整线程池中线程的数量的线程池。即该线程池中的线程数量不确定，是根据实际情况动态调整的。
        栗子：假如该线程池中的所有线程都正在工作，而此时有新任务提交，那么将会创建新的线程去处理该任务，而此时假如之前有一些线程完成了任务，现在又有新任务提交，
        那么将不会创建新线程去处理，而是复用空闲的线程去处理新任务。那么此时有人有疑问了，那这样来说该线程池的线程岂不是会越集越多？其实并不会，因为线程池中的线程都有一个“保持活动时间”的参数，
        通过配置它，如果线程池中的空闲线程的空闲时间超过该“保存活动时间”则立刻停止该线程，而该线程池默认的“保持活动时间”为60s。*/
        bt_newCachedThreadPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CachedThreadPool = Executors.newCachedThreadPool();
                for (int i = 0; i < 10; i++) {
                    final int index = i;
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    CachedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String name = Thread.currentThread().getName();
                            Log.e(TAG, "newCachedThreadPool 线程: " + name + "正在执行第" + index + "个任务");
                            try {
                                long time = index * 500;
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Log.e(TAG, "newCachedThreadPool 线程: " + name + "结束第" + index + "个任务");
                        }
                    });
                }
            }
        });
     /*   4、newScheduledThreadPool() ：
        作用：该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池。
        DelayedWorkQueue 等待队列*/

        bt_newScheduledThreadPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);//设置线程数3
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    scheduledExecutorService.schedule(new Runnable() {
                        @Override
                        public void run() {
                            String name = Thread.currentThread().getName();
                            Log.e(TAG, "newScheduledThreadPool 线程: " + name + "正在执行第" + index + "个任务");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 2, TimeUnit.SECONDS);
                }
                //延迟1秒后，每隔2秒执行一次该任务 这可以搞循环了 和rxjava的那个interval像
       /*         scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "scheduleAtFixedRate run:---------------- ");
                    }
                }, 1, 2, TimeUnit.SECONDS);*/


            }
        });
    /*    5、newSingleThreadScheduledExecutor() ：
        作用：该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池。只不过和上面的区别是该线程池大小为1，而上面的可以指定线程池的大小。*/
        bt_newSingleThreadScheduledExecutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                //这种调用execute就和上面的一样了就是一个单线程线程池
//                scheduledExecutorService.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.e(TAG, "newSingleThreadScheduledExecutor run:---------------- ");
//                    }
//                });
                //延迟两秒执行一个任务
                scheduledExecutorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "newSingleThreadScheduledExecutor run:---------------- ");
                    }
                }, 2, TimeUnit.SECONDS);
            }
        });
//        1、shutdown()方法在终止前允许执行以前提交的任务。
//        2、shutdownNow()方法则是阻止正在任务队列中等待任务的启动并试图停止当前正在执行的任务。
        bt_Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: 开始停止线程池");
                // fixedThreadPool.shutdown();
                 fixedThreadPool.shutdownNow();//还是这个好使
                //SingleThreadExecutor.shutdown();
               // SingleThreadExecutor.shutdownNow();
               // CachedThreadPool.shutdown();
               // CachedThreadPool.shutdownNow();
            }
        });

    }

    @Override
    protected void loadData() {

    }
}
