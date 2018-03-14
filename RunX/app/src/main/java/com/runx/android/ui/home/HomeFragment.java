package com.runx.android.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.flyco.tablayout.SlidingTabLayout;
import com.runx.android.R;
import com.runx.android.base.BaseFragment;
import com.runx.android.ui.main.TabPageAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/5 0005.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_page)
    ViewPager viewPage;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private TabPageAdapter adapter;
    private String[] mTitles;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initFragment() {
        mTitles = getResources().getStringArray(R.array.home_tabs);

        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(new HomeChildFragment());
        }
        Log.e("====>",mFragments.size()+"---"+mTitles.length);
        adapter = new TabPageAdapter(getChildFragmentManager(), mFragments, mTitles);
        viewPage.setAdapter(adapter);
        viewPage.setCurrentItem(0);
        viewPage.setOffscreenPageLimit(adapter.getCount());
        tabLayout.setViewPager(viewPage, mTitles);
    }
}
