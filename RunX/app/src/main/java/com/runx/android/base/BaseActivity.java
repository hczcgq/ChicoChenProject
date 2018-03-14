package com.runx.android.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.jaeger.library.StatusBarUtil;
import com.runx.android.R;
import com.runx.android.RunxApplication;
import com.runx.android.common.constant.Constant;
import com.runx.android.common.util.ToastUtils;
import com.runx.android.di.component.ActivityComponent;
import com.runx.android.di.component.DaggerActivityComponent;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by 陈国权 on 2018/3/2 0002.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        if (getContentViewId() != -1) {
            setContentView(getContentViewId());
        }
        ButterKnife.bind(this);
        ActivityComponent component = DaggerActivityComponent.builder()
                .appComponent(RunxApplication.getInstance().getAppComponent())
                .build();
        injectActivityComponent(component);
        setStatusBar();
        initActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MANService manService = MANServiceProvider.getService();
        manService.getMANPageHitHelper().pageAppear(this);
        MobclickAgent.onPageStart(this.getClass().getName());
        MobclickAgent.onResume(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        MANService manService = MANServiceProvider.getService();
        manService.getMANPageHitHelper().pageDisAppear(this);
        MobclickAgent.onPageEnd(this.getClass().getName());
        MobclickAgent.onPause(this);
    }


    /**
     * 获取传递数据
     */
    public void getIntentData() {
    }

    /**
     * 获取布局文件ID
     *
     * @return
     */
    public abstract int getContentViewId();

    /**
     * 初始化Activity
     */
    public abstract void initActivity();

    /**
     * 注入网络请求
     *
     * @param component
     */
    public void injectActivityComponent(ActivityComponent component) {

    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 1);
    }


    /**
     * 检查权限
     *
     * @param permissions
     */
    protected void checkPermission(String[] permissions) {
        List<String> permissionList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permission);
                }
            }

            if (permissions != null && permissionList.size() > 0) {
                ActivityCompat.requestPermissions(this,
                        permissionList.toArray(new String[permissionList.size()])
                        , Constant.REQUEST_CODE_PERMISSION);
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != Constant.REQUEST_CODE_PERMISSION) {
            return;
        }

        if (permissions.length <= 0 || grantResults.length <= 0) {
            return;
        }

        for (int i = 0; i < permissions.length; i++) {
            if (Manifest.permission.CAMERA.equals(permissions[i])) {
                if (grantResults.length >= i && grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtils.showShortToast(this, R.string.system_permission_limit);
                }
            }
        }
    }
}
