package com.mcntraining.demo2.CheckConnectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by HAMDAN on 27-Apr-18.
 */

public class ConnectivityReceiver extends BroadcastReceiver
{
    public  static ConnectivityReceiverListener connectivityReceiverListener;
    public ConnectivityReceiver()
    {
        super();
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
        boolean isConnected=activeNetwork !=null && activeNetwork.isConnectedOrConnecting();

        if(connectivityReceiverListener !=null)
        {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }

    }
    public static boolean isConnected()
    {
        ConnectivityManager cm=(ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
        return activeNetwork !=null && activeNetwork.isConnectedOrConnecting();

    }
    public interface ConnectivityReceiverListener
    {
       public  void onNetworkConnectionChanged(boolean isConnected);
    }
}
