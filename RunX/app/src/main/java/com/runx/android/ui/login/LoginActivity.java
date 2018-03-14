package com.runx.android.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.runx.android.R;
import com.runx.android.base.BasePresenterActivity;
import com.runx.android.di.component.ActivityComponent;
import com.runx.android.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/5 0005.
 */

public class LoginActivity extends BasePresenterActivity<LoginPresenter> implements LoginConstract.View {


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void injectActivityComponent(ActivityComponent component) {
        super.injectActivityComponent(component);
        component.inject(this);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void initActivity() {

    }

    @OnClick(R.id.btn_event)
    public void onViewClicked() {
        Log.e("====>","11111");
        EventBus.getDefault().post(new MessageEvent("my name is chenguoquan"));
    }
}
