package com.example.lurenman.threaddemo.activity;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 创建日期：2019/5/13
 * 作者:baiyang
 * 参考https://www.jianshu.com/p/55221d045f39
 * https://blog.csdn.net/javazejian/article/details/50896505
 */
public class FutureTaskActivity extends BaseNewActivity {
    private static final String TAG = "FutureTaskActivity";
    private Button btn_click;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "FutureTask";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_future;
    }

    @Override
    protected void initView() {
        btn_click = (Button) findViewById(R.id.btn_click);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initListenter() {
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallableTest1 callableTest1 = new CallableTest1();
                FutureTask<Integer> futureTask = new FutureTask<>(callableTest1);
                Thread thread = new Thread(futureTask);
                thread.start();
                Log.e(TAG, "onClick: ----" + "thread start");
                try {
                    Log.e(TAG, "onClick:-----futureTask.get()调用前");
//                    Thread.sleep(1000);
//                    thread.interrupt();//线程打断测试
                    //前面执行是异步的，到这是阻塞等待结果
                    Integer integer = futureTask.get();
                    //futureTask.cancel(true);
                    // 这个是如果过1一秒没有结果就抛出TimeoutException异常
                   // Integer integer = futureTask.get(1000, TimeUnit.MILLISECONDS);
                    Log.e(TAG, "onClick:----- result:" + integer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e(TAG, "onClick: ------" + "InterruptedException:" + e.getMessage());
                } catch (ExecutionException e) {
                    Log.e(TAG, "onClick: ------" + "ExecutionException：" + e.getMessage());
                    e.printStackTrace();
                }
//                catch (TimeoutException e) {
//                    Log.e(TAG, "onClick: ------" + "TimeoutException：" + e.getMessage());
//                    e.printStackTrace();
//                }
            }
        });
    }

    private void test1() {

    }

    class CallableTest1 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Log.e(TAG, "CallableTest1: " + "正在计算");
            Thread.sleep(3000);
            return 1;
        }
    }
}
