package com.chic.android.ui;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.TextView;

import com.chic.android.R;
import com.chic.android.base.BaseActivity;
import com.chic.android.ui.feature.FeatureFragment;
import com.chic.android.ui.open.OpenFragment;
import com.chic.android.ui.widget.WidgetFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_FEATURE = 0;
    private static final int FRAGMENT_OPEN = 1;
    private static final int FRAGMENT_WIDGET = 2;

    private OpenFragment openFragment;
    private FeatureFragment featureFragment;
    private WidgetFragment widgetFragment;

    @BindView(R.id.bottom_view)
    BottomNavigationViewEx bottomView;
    @BindView(R.id.toolbar_title)
    TextView titleText;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initActivity() {
        bottomView.enableAnimation(false);
        bottomView.enableShiftingMode(false);
        bottomView.enableItemShiftingMode(false);
        bottomView.setOnNavigationItemSelectedListener(this);
        showFragment(FRAGMENT_FEATURE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_feature:
                showFragment(FRAGMENT_FEATURE);
                break;
            case R.id.navigation_open:
                showFragment(FRAGMENT_OPEN);
                break;
            case R.id.navigation_widget:
                showFragment(FRAGMENT_WIDGET);
                break;
            default:
                break;
        }
        return true;
    }

    private void showFragment(int index) {
        setMainTitle(index);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRAGMENT_FEATURE:
                if (featureFragment == null) {
                    featureFragment = new FeatureFragment();
                    ft.add(R.id.fragment_content, featureFragment, FeatureFragment.class.getName());
                } else {
                    ft.show(featureFragment);
                }
                break;

            case FRAGMENT_OPEN:
                if (openFragment == null) {
                    openFragment = new OpenFragment();
                    ft.add(R.id.fragment_content, openFragment, OpenFragment.class.getName());
                } else {
                    ft.show(openFragment);
                }
                break;

            case FRAGMENT_WIDGET:
                if (widgetFragment == null) {
                    widgetFragment = new WidgetFragment();
                    ft.add(R.id.fragment_content, widgetFragment, WidgetFragment.class.getName());
                } else {
                    ft.show(widgetFragment);
                }
                break;
            default:
                break;

        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (featureFragment != null) {
            ft.hide(featureFragment);
        }
        if (openFragment != null) {
            ft.hide(openFragment);
        }
        if (widgetFragment != null) {
            ft.hide(widgetFragment);
        }
    }

    private void setMainTitle(int index) {
        switch (index) {
            case FRAGMENT_FEATURE:
                titleText.setText(R.string.main_tab_feature);
                break;
            case FRAGMENT_OPEN:
                titleText.setText(R.string.main_tab_open);
                break;
            case FRAGMENT_WIDGET:
                titleText.setText(R.string.main_tab_widget);
                break;
        }
    }
}
