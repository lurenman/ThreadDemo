package com.example.lurenman.threaddemo.activity;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;
import com.example.lurenman.threaddemo.runnable.FairLockRunnable;
import com.example.lurenman.threaddemo.runnable.JoinRunnable;
import com.example.lurenman.threaddemo.runnable.LockRunnableTest1;
import com.example.lurenman.threaddemo.runnable.LockRunnableTest2;
import com.example.lurenman.threaddemo.runnable.LockRunnableTest3;
import com.example.lurenman.threaddemo.runnable.LockRunnableTest4;
import com.example.lurenman.threaddemo.runnable.TicketLockRunnable;
import com.example.lurenman.threaddemo.runnable.TicketRunnable;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建日期：2019/5/8
 * 作者:baiyang
 * 重入锁
 * 参考https://blog.csdn.net/javazejian/article/details/50878665
 * https://blog.csdn.net/Somhu/article/details/78874634
 */
public class ReentrantLockActivity extends BaseNewActivity {
    private static final String TAG = "ReentrantLockActivity";
    private Button btn_test;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "ReentrantLock";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_reentrantlock;
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
                //testTickIssue();
                // reentrantLock();
                // fairLocks(true);
                // test1();
                //test2();
                //test3();
                //test4();
                joinMethod();
            }
        });
    }

    /**
     * 模拟卖票线程操作统一数据的问题
     * 同步可以解决当前问题
     */
    private void testTickIssue() {
        TicketRunnable ticketRunnable = new TicketRunnable();
        Thread thread1 = new Thread(ticketRunnable);
        Thread thread2 = new Thread(ticketRunnable);
        Thread thread3 = new Thread(ticketRunnable);
        Thread thread4 = new Thread(ticketRunnable);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    /**
     * 卖票例子重入锁解决线程安全问题
     */
    private void reentrantLock() {
        TicketLockRunnable ticketLockRunnable = new TicketLockRunnable();
        Thread thread1 = new Thread(ticketLockRunnable);
        Thread thread2 = new Thread(ticketLockRunnable);
        Thread thread3 = new Thread(ticketLockRunnable);
        Thread thread4 = new Thread(ticketLockRunnable);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

    /**
     * 公平锁测试，如果不是公平锁 thread2可能获取不到锁
     * 为true的时候thread1和thread2 交换获取到锁
     *
     * @param fair true 公平锁 默认 false
     */
    private void fairLocks(boolean fair) {
        FairLockRunnable fairLockRunnable = new FairLockRunnable(new ReentrantLock(fair));
        Thread thread1 = new Thread(fairLockRunnable, "线程1");
        Thread thread2 = new Thread(fairLockRunnable, "线程2");
        thread1.start();
        thread2.start();
    }

    /**
     * 测试几个
     * lock.isHeldByCurrentThread()
     * lock.hasQueueThread(Thread thread)
     * lock.isLocked()
     */
    private void test1() {
        LockRunnableTest1 lockRunnableTest1 = new LockRunnableTest1();
        Thread thread = new Thread(lockRunnableTest1);
        thread.start();
    }

    /**
     * 死锁中断测试
     */
    private void test2() {
        LockRunnableTest2 runnableType1 = new LockRunnableTest2(LockRunnableTest2.LockType.TYPE1);
        LockRunnableTest2 runnableType2 = new LockRunnableTest2(LockRunnableTest2.LockType.TYPE2);

        Thread thread1 = new Thread(runnableType1);
        Thread thread2 = new Thread(runnableType2);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    /**
     * tryLock()测试
     */
    private void test3() {
        LockRunnableTest3 lockRunnableTest3 = new LockRunnableTest3();
        Thread thread1 = new Thread(lockRunnableTest3, "线程1");
        Thread thread2 = new Thread(lockRunnableTest3, "线程2");
        thread1.start();
        thread2.start();
    }

    /**
     * Condition 测试
     */
    private void test4() {
        LockRunnableTest4 lockRunnableTest4 = new LockRunnableTest4();
        Thread thread = new Thread(lockRunnableTest4);
        thread.start();
        ReentrantLock lock = null;
        try {
            Thread.sleep(1000);
            lock = lockRunnableTest4.getLock();
            Condition condition = lockRunnableTest4.getCondition();
            lock.lock();
            //唤醒 从该条件的等待池中随机地选择一个线程，解除其阻塞状态（对应notify()方法）
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    /**
     *   thread1.join(1000);这个代表等一秒，之后就执行下面
     *   如果线程在给定的超时时间里没有终止，那么将会从该超时方法中返回
     */
    private void joinMethod(){
        JoinRunnable joinRunnable = new JoinRunnable();
        Thread thread1 = new Thread(joinRunnable, "线程1");
        thread1.start();
        try {
            //thread1.join();
            thread1.join(1000);
            Log.e(TAG, "joinMethod: -------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
