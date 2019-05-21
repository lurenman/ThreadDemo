package com.example.lurenman.threaddemo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lurenman.threaddemo.R;
import com.example.lurenman.threaddemo.aidl.IMyAidlInterface;
import com.example.lurenman.threaddemo.service.AidlTestService;

/**
 * 创建日期：2019/5/20
 * 作者:baiyang
 * aidl测试
 * 参考https://blog.csdn.net/luoyanglizi/article/details/51980630
 * https://blog.csdn.net/luoyanglizi/article/details/52029091
 * 两个 app的要是调用的化，本项目service里开启 ，另一个app必须有个相同的包名文件
 */
public class AidlActivity extends BaseNewActivity implements View.OnClickListener {

    private Button btn_test;
    private IMyAidlInterface iMyAidlInterface;
    private ServiceConnection mServiceConnection;
    private boolean isbind = false;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "aidl";
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
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(this, AidlTestService.class);
        isbind = bindService(intent, mServiceConnection, BIND_AUTO_CREATE);

    }

    @Override
    protected void initListenter() {
        btn_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                test1();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (isbind && mServiceConnection != null) {
            unbindService(mServiceConnection);
        }
        super.onDestroy();

    }

    private void test1() {
        try {
//            String name = iMyAidlInterface.getName();
//            Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
            int i = iMyAidlInterface.add(1, 2);
            Toast.makeText(mContext, i+"", Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
