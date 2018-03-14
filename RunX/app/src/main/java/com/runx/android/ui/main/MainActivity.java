package com.runx.android.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.runx.android.R;
import com.runx.android.base.BaseActivity;
import com.runx.android.eventbus.MessageEvent;
import com.runx.android.ui.discover.DiscoverFragment;
import com.runx.android.ui.home.HomeFragment;
import com.runx.android.ui.live.LiveFragment;
import com.runx.android.ui.mine.MineFragment;
import com.runx.android.ui.quizzes.QuizzesFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import butterknife.BindView;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_QUIZZES = 1;
    private static final int FRAGMENT_LIVE = 2;
    private static final int FRAGMENT_DISCOVER = 3;
    private static final int FRAGMENT_MINE = 4;

    private HomeFragment homeFragment;
    private QuizzesFragment quizzesFragment;
    private LiveFragment liveFragment;
    private DiscoverFragment discoverFragment;
    private MineFragment mineFragment;

    @BindView(R.id.bottom_view)
    BottomNavigationViewEx bottomView;

    private long exitTime;  //退出时间

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initActivity() {
        bottomView.enableAnimation(false);
        bottomView.enableShiftingMode(false);
        bottomView.enableItemShiftingMode(false);
        bottomView.setOnNavigationItemSelectedListener(this);
        showFragment(FRAGMENT_HOME);
        //申请必须权限
        checkPermission(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE});

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showFragment(FRAGMENT_HOME);
                break;
            case R.id.navigation_quizzes:
                showFragment(FRAGMENT_QUIZZES);
                break;
            case R.id.navigation_live:
                showFragment(FRAGMENT_LIVE);
                break;
            case R.id.navigation_discover:
                showFragment(FRAGMENT_DISCOVER);
                break;
            case R.id.navigation_mine:
                showFragment(FRAGMENT_MINE);
                break;
        }
        return true;
    }


    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRAGMENT_HOME:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.fragment_content, homeFragment, HomeFragment.class.getName());
                } else {
                    ft.show(homeFragment);
                }
                break;

            case FRAGMENT_QUIZZES:
                if (quizzesFragment == null) {
                    quizzesFragment = new QuizzesFragment();
                    ft.add(R.id.fragment_content, quizzesFragment, QuizzesFragment.class.getName());
                } else {
                    ft.show(quizzesFragment);
                }
                break;

            case FRAGMENT_LIVE:
                if (liveFragment == null) {
                    liveFragment = new LiveFragment();
                    ft.add(R.id.fragment_content, liveFragment, LiveFragment.class.getName());
                } else {
                    ft.show(liveFragment);
                }
                break;

            case FRAGMENT_DISCOVER:
                if (discoverFragment == null) {
                    discoverFragment = new DiscoverFragment();
                    ft.add(R.id.fragment_content, discoverFragment, DiscoverFragment.class.getName());
                } else {
                    ft.show(discoverFragment);
                }
                break;

            case FRAGMENT_MINE:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.fragment_content, mineFragment, MineFragment.class.getName());
                } else {
                    ft.show(mineFragment);
                }
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (quizzesFragment != null) {
            ft.hide(quizzesFragment);
        }

        if (liveFragment != null) {
            ft.hide(liveFragment);
        }

        if (discoverFragment != null) {
            ft.hide(discoverFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
    }


    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, R.string.double_click_exit, Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void getMessageEvent(MessageEvent event) {
        Log.e("====>",event.message);
    }
}
