package com.example.lurenman.threaddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.lurenman.threaddemo.aidl.IMyAidlInterface;

/**
 * 创建日期：2019/5/20
 * 作者:baiyang
 */
public class AidlTestService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 和远程服务交接的接口
     */
    class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public String getName() throws RemoteException {
            return "test";
        }

        @Override
        public int add(int x, int y) throws RemoteException {
            return x + y;
        }
    }
}
