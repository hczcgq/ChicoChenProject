package com.chic.android.ui.feature.suspend;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chic.android.R;
import com.chic.android.base.BaseActivity;
import com.chic.android.common.glide.GlideApp;
import butterknife.BindView;


/**
 * @author: 陈国权
 * @date: 2018/4/13 0013 下午 15:59
 * @description: 标题悬浮
 */

public class SuspendActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.feed_list)
    RecyclerView mFeedList;
    @BindView(R.id.iv_avatar)
    ImageView mSuspensionIv;
    @BindView(R.id.tv_nickname)
    TextView mSuspensionTv;
    @BindView(R.id.top_divider)
    View topDivider;
    @BindView(R.id.suspension_bar)
    RelativeLayout mSuspensionBar;

    private int mSuspensionHeight;
    private int mCurrentPosition = 0;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_suspend;
    }

    @Override
    protected void initActivity() {
        configToolbar();
        configRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_suspend, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_more:
                Intent intent = new Intent(SuspendActivity.this,SuspendMoreActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
        SuspendAdapter adapter = new SuspendAdapter();
        mFeedList.setLayoutManager(linearLayoutManager);
        mFeedList.setAdapter(adapter);
        mFeedList.setHasFixedSize(true);

        mFeedList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight = mSuspensionBar.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view != null) {
                    if (view.getTop() <= mSuspensionHeight) {
                        mSuspensionBar.setY(-(mSuspensionHeight - view.getTop()));
                    } else {
                        mSuspensionBar.setY(0);
                    }
                }

                if (mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    mSuspensionBar.setY(0);

                    updateSuspensionBar();
                }
            }
        });
    }

    private void updateSuspensionBar() {
        GlideApp.with(this)
                .load(getAvatarResId(mCurrentPosition))
                .centerInside()
                .fitCenter()
                .into(mSuspensionIv);

        mSuspensionTv.setText("Taeyeon " + mCurrentPosition);
    }

    private int getAvatarResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;
            default:
                break;
        }
        return 0;
    }

}
