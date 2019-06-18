package com.example.lurenman.threaddemo.activity;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;
import com.example.lurenman.threaddemo.runnable.JionRunnable;
import com.example.lurenman.threaddemo.runnable.YieldRunnable;

/**
 * 创建日期：2019/6/18
 * 作者:baiyang
 * 参考https://blog.csdn.net/leonardo9029/article/details/46343289
 * jion 和yield 还有sleep 都是抢占cpu执行资源的 好好理解这句话
 * sleep 略
 */
public class JionYieldSleepActivity extends BaseNewActivity {
    private static final String TAG = "JionYieldSleepActivity";
    private Button btn_test;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "JionYieldSleep";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_common;
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
//                try {
//                    test1();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                test3();
            }
        });
    }

    /**
     * jion测试
     * 说白了就是获取到cpu执行权
     */
    private void test1() throws InterruptedException {
        JionRunnable jionRunnable = new JionRunnable();
        Thread thread1 = new Thread(jionRunnable, "thread1");
        Thread thread2 = new Thread(jionRunnable, "thread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Log.e(TAG, "test1:----------------------- ");

    }

    /**
     * 这种是等thread1 执行完才搞下面thread2.start();
     *
     * @throws InterruptedException
     */
    private void test2() throws InterruptedException {
        JionRunnable jionRunnable = new JionRunnable();
        Thread thread1 = new Thread(jionRunnable, "thread1");
        Thread thread2 = new Thread(jionRunnable, "thread2");
        thread1.start();
        thread1.join();
        thread2.start();
        Log.e(TAG, "test2:----------------------- ");
    }

    /**
     * yield 优先级高的优先抢占cpu执行权
     * 用了yield（）之后，cpu会多交出一些时间给ti优先执行。(多交出效果就不一定，是概率事件)
     */
    private void test3() {
        YieldRunnable yieldRunnable = new YieldRunnable();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Log.e(TAG, "run: thread name " + Thread.currentThread().getName());
                }
            }
        }, "thread1");
        Thread thread2 = new Thread(yieldRunnable, "thread2");
        thread1.setPriority(Thread.MAX_PRIORITY);//设置最高优先级10
        thread2.setPriority(Thread.MIN_PRIORITY);//设置最低优先级1


        thread2.start();
        thread1.start();

    }
//    而Thread.sleep()方法使当前线程进入停滞状态，所以执行sleep()的线程在指定的时间内肯定不会执行,
//    同时sleep函数不会释放锁资源；sleep可使优先级低的线程得到执行的机会，
//    当然也可以让同优先级和高优先级的线程有执行的机会。


}
