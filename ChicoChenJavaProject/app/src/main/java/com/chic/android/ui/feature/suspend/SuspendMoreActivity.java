package com.chic.android.ui.feature.suspend;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chic.android.R;
import com.chic.android.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 陈国权
 * @date: 2018/4/13 0013 下午 16:37
 * @description:
 */

public class SuspendMoreActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.feed_list)
    RecyclerView mSuspendList;
    @BindView(R.id.tv_time)
    TextView mSuspensionTv;
    @BindView(R.id.suspension_bar)
    RelativeLayout mSuspensionBar;

    private int mCurrentPosition = 0;
    private int mSuspensionHeight;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_suspend_more;
    }

    @Override
    protected void initActivity() {
        configToolbar();
        configRecyclerView();
    }

    private void configToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
                | ActionBar.DISPLAY_SHOW_HOME);
        mToolbar.setNavigationIcon(R.drawable.icon_back);
        mToolbar.setTitle("悬浮标题");
    }

    private void configRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final SuspendMoreAdapter adapter = new SuspendMoreAdapter();
        mSuspendList.setLayoutManager(linearLayoutManager);
        mSuspendList.setAdapter(adapter);
        mSuspendList.setHasFixedSize(true);

        mSuspendList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight = mSuspensionBar.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (adapter.getItemViewType(mCurrentPosition + 1) == SuspendMoreAdapter.TYPE_TIME) {
                    View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                    if (view != null) {
                        if (view.getTop() <= mSuspensionHeight) {
                            mSuspensionBar.setY(-(mSuspensionHeight - view.getTop()));
                        } else {
                            mSuspensionBar.setY(0);
                        }
                    }
                }

                if (mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    mSuspensionBar.setY(0);

                    updateSuspensionBar();
                }
            }
        });
        updateSuspensionBar();
    }

    private void updateSuspensionBar() {
        mSuspensionTv.setText(getTime(mCurrentPosition));
    }

    private String getTime(int position) {
        return "NOVEMBER " + (1 + position / 4);
    }

}
