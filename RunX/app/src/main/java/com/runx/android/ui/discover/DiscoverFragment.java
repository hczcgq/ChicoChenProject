package com.runx.android.ui.discover;

import android.util.Log;

import com.runx.android.R;
import com.runx.android.base.BaseFragment;

/**
 * Created by Administrator on 2018/3/5 0005.
 */

public class DiscoverFragment extends BaseFragment {
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initFragment() {
        Log.e("====>","processViewData-----");
    }
}
