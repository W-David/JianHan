package com.example.jianhan.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null){
             NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
            for(NetworkInfo info : networkInfos){
                if(info.getState() == NetworkInfo.State.CONNECTED) return true;
            }
        }
        return false;
    }
}
