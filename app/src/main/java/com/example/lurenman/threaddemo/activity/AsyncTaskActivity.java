package com.example.lurenman.threaddemo.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: baiyang.
 * Created on 2017/11/7.
 */

public class AsyncTaskActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "AsyncTaskActivity";
    private Button bt_click;
    AsyncTask<String, Integer, String> aaaaaaaaa;
    AsyncTask<String, Integer, String> bbbbbbbbb;
    AsyncTask<String, Integer, String> ccccccccc;
    AsyncTask<String, Integer, String> ddddddddd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        bt_click = (Button) findViewById(R.id.bt_click);
        bt_click.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == bt_click) {
            aaaaaaaaa = new AysnTaskDemo().execute("aaaaaaaaa");
            bbbbbbbbb = new AysnTaskDemo().execute("bbbbbbbbb");
            ccccccccc = new AysnTaskDemo().execute("ccccccccc");
            ddddddddd = new AysnTaskDemo().execute("ddddddddd");

            //在安卓3.0以上要实现并发可以AsyncTask.THREAD_POOL_EXECUTOR他、用这个改下线程池来达到并发的效果
//            new AysnTaskDemo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"aaaaaaaaa");
//            new AysnTaskDemo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"bbbbbbbbb");
//            new AysnTaskDemo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"ccccccccc");
//            new AysnTaskDemo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"ddddddddd");
        }
    }

    public class AysnTaskDemo extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(3000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e(TAG, s + "-------" + getNowDate());
            //就像这样要是引用activity的东西就会造成内存泄漏，
            // 所以在activity销毁的时候取消任务，和这个类定义成静态的，都可以防止内存泄漏。
          //  bt_click.setText(s);

        }

        /**
         * onCancelled是可以选择性覆写的方法
         * 在主线程中,当异步任务被取消时,该方法将被调用,
         * 要注意的是这个时onPostExecute将不会被执行
         */
        @Override
        protected void onCancelled() {
            super.onCancelled();
            // Log.e(TAG, "onCancelled: " + "取消任务执行了");
        }

        //这个取消返回值就只能返回下次要执行的任务名字，之后的都返回null。。。。。
        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            Log.e(TAG, "onCancelled: " + "取消任务" + s);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //觉得非空还是判读一下比较好
        aaaaaaaaa.cancel(true);
        bbbbbbbbb.cancel(true);
        ccccccccc.cancel(true);
        ddddddddd.cancel(true);
//        if (aaaaaaaaa!=null)
//        {
//            aaaaaaaaa.cancel(true);
//        }
//        if (bbbbbbbbb!=null)
//        {

//            bbbbbbbbb.cancel(true);
//        }
//        if (ccccccccc!=null)
//        {
//            ccccccccc.cancel(true);
//        }
//        if (ddddddddd!=null)
//        {
//            ddddddddd.cancel(true);
//        }

    }
}
