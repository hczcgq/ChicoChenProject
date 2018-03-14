package com.chic.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

public abstract class BaseFragment extends Fragment{

    protected abstract int getContentViewId();

    protected abstract void initFragment();

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentViewId(), null);
        unbinder = ButterKnife.bind(this, rootView);
        initFragment();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
