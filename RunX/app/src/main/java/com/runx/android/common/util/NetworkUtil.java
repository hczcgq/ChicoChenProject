package com.runx.android.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.runx.android.RunxApplication;

/**
 * Created by 陈国权 on 2018/3/2 0002.
 */

public class NetworkUtil {

    /**
     * 判断网络是否有效
     * @return
     */
    public static boolean isNetworkValid() {
        ConnectivityManager connectivityManager = (ConnectivityManager) RunxApplication.getInstance().getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }
}
