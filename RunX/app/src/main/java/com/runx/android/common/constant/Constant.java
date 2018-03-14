package com.runx.android.common.constant;

import android.os.Environment;

/**
 * Created by 陈国权 on 2018/3/2 0002.
 * 常量
 */

public class Constant {

    //sd卡路径
    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    //http请求缓存
    public static final String HTTP_RESPONSE_CACHE = "HttpResponseCache";

    //http缓存最大Size
    public static final int HTTP_RESPONSE_CACHE_MAX_SIZE = 10 * 1024 * 1024;


    /**
     * Intent常量
     */
    public static final String INTENT_URL = "url";


    /**
     * RequestCode常量
     */
    public static final int REQUEST_CODE_PERMISSION = 100;

    /**
     * SharePreference
     */
    public static final String PRE_GUIDE_STATUE = "Guide_Statue";  //引导页状态，true不展示
}
