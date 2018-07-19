package com.mcntraining.demo2.CheckConnectivity;

import android.app.Application;

/**
 * Created by HAMDAN on 27-Apr-18.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {

        super.onCreate();
        mInstance=this;
    }
    public static synchronized MyApplication getInstance()
    {
        return mInstance;
    }
    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener)
    {
        ConnectivityReceiver.connectivityReceiverListener=listener;
    }
}
