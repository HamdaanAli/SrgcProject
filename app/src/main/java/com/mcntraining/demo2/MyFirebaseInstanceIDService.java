package com.mcntraining.demo2;

import android.app.Service;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by user on 4/2/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        Log.d("Token:",refreshedToken);
        // System.out.println(refreshedToken);
        sendTokenToServer(refreshedToken);
    }

    private void sendTokenToServer(String refreshedToken) {
        if(refreshedToken!=null){

        }
    }
}
