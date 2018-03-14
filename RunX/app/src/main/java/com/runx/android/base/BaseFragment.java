package com.runx.android.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.runx.android.RunxApplication;
import com.runx.android.di.component.ActivityComponent;
import com.runx.android.di.component.DaggerActivityComponent;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 陈国权 on 2018/3/2 0002.
 */

public abstract class BaseFragment extends Fragment {
    protected abstract int getContentViewId();

    protected abstract void initFragment();

    private Unbinder unbinder;

    protected void getIntentData() {
    }

    /**
     * 注入网络请求
     *
     * @param component
     */
    public void injectFragmentComponent(ActivityComponent component) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentViewId(), null);
        unbinder = ButterKnife.bind(this, rootView);
        ActivityComponent component = DaggerActivityComponent.builder()
                .appComponent(RunxApplication.getInstance().getAppComponent())
                .build();
        injectFragmentComponent(component);
        initFragment();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getName());
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getName());
    }
}
