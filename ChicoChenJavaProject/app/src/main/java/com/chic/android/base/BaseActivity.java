package com.chic.android.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chic.android.R;
import com.jaeger.library.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

public abstract class BaseActivity extends AppCompatActivity {



    /**
     * 获取LayoutId
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 初始化Activity
     */
    protected abstract void initActivity();

    /**
     * 设置状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 1);
        StatusBarUtil.setLightMode(this);
    }

    protected void getIntentData(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        setStatusBar();
        initActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
