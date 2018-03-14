package com.runx.android;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.runx.android.common.util.AppUtil;
import com.runx.android.di.component.AppComponent;
import com.runx.android.di.component.DaggerAppComponent;
import com.runx.android.di.module.ApiServiceModule;
import com.runx.android.di.module.AppModule;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;


/**
 * Created by Administrator on 2018/3/2 0002.
 */

public class RunxApplication extends Application {

    private static final String TAG = "MyMessageReceiver";

    private static RunxApplication mInstance;

    public static RunxApplication getInstance() {
        return mInstance;
    }

    public Context getAppContext() {
        return mInstance.getAppContext();
    }


    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initCloudChannel(this);
        MultiDex.install(this);
        initCloudMan();
        initUmeng();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.e(TAG, "init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
//        MiPushRegister.register(applicationContext, "XIAOMI_ID", "XIAOMI_KEY"); // 初始化小米辅助推送
//        HuaWeiRegister.register(applicationContext); // 接入华为辅助推送
//        GcmRegister.register(applicationContext, "send_id", "application_id"); // 接入FCM/GCM初始化推送
    }

    /**
     * 初始化Mobile Analytics服务
     */
    private void initCloudMan() {
        // 获取MAN服务
        MANService manService = MANServiceProvider.getService();

        // 打开调试日志
        manService.getMANAnalytics().turnOnDebug();

        // MAN初始化方法之一，从AndroidManifest.xml中获取appKey和appSecret初始化
        manService.getMANAnalytics().init(this, getApplicationContext());

        // 若需要关闭 SDK 的自动异常捕获功能可进行如下操作,详见文档5.4
        manService.getMANAnalytics().turnOffCrashReporter();

        // 通过此接口关闭页面自动打点功能，详见文档4.2
        manService.getMANAnalytics().turnOffAutoPageTrack();

        // 若既没有设置AndroidManifest.xml 中的 android:versionName，也没有调用setAppVersion，appVersion则为null
        manService.getMANAnalytics().setAppVersion(AppUtil.getVersionName(this));
    }

    /**
     * 初始化友盟
     */
    private void initUmeng() {
        UMConfigure.setLogEnabled(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
    }
}
