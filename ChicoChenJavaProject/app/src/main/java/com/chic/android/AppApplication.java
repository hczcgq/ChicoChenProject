package com.chic.android;

import android.app.Application;

import com.chic.android.ui.open.objectbox.MyObjectBox;

import io.objectbox.BoxStore;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

public class AppApplication extends Application{

    private static AppApplication instance;
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        boxStore = MyObjectBox.builder().androidContext(AppApplication.this).build();

    }

    public static AppApplication getInstance() {
        if (instance == null) {
            instance = new AppApplication();
        }
        return instance;
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
