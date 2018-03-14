package com.runx.android.common.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by 陈国权 on 2018/3/7 0007.
 */

public class ToastUtils {
    public static void showShortToast(Context mContext, String msg) {
        showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    public static void showShortToast(Context mContext, int strRes) {
        showShortToast(mContext, mContext.getResources().getString(strRes));
    }

    public static void showLongToast(Context mContext, String msg) {
        showToast(mContext, msg, Toast.LENGTH_LONG);
    }

    public static void showLongToast(Context mContext, int strRes) {
        showLongToast(mContext, mContext.getResources().getString(strRes));
    }

    public static void showToast(Context context, String msg, int duration) {
        showToast(context, msg, duration, Gravity.CENTER);
    }

    public static void showToast(Context context, String msg, int duration, int gravity) {
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }
}
