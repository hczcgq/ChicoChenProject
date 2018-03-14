package com.runx.android.di.component;

import com.runx.android.di.ActivityScoped;
import com.runx.android.ui.login.LoginActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/3/2 0002.
 */

@ActivityScoped
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(LoginActivity activity);
}
