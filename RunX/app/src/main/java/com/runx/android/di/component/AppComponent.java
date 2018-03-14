package com.runx.android.di.component;

import com.runx.android.di.module.ApiServiceModule;
import com.runx.android.di.module.AppModule;
import com.runx.android.http.APIService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/3/2 0002.
 */

@Component(modules = {AppModule.class, ApiServiceModule.class})
@Singleton
public interface AppComponent {
    APIService getService();
}
