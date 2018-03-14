package com.chic.android.ui.open.objectbox;

import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.chic.android.AppApplication;
import com.chic.android.R;
import com.chic.android.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import io.objectbox.Box;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

public class ObjectBoxActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private UserAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_objectbox;
    }

    @Override
    protected void initActivity() {


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
                | ActionBar.DISPLAY_SHOW_HOME);
        mToolbar.setNavigationIcon(R.drawable.icon_back);
        toolbarTitle.setText(R.string.address_book);

        Box<User> boxStore = AppApplication.getInstance().getBoxStore().boxFor(User.class);
        List<User> userList = boxStore.getAll();

        if (userList != null && userList.size() > 0) {
            return;
        }

        if (mAdapter == null) {
            mAdapter = new UserAdapter(R.layout.item_user, userList);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add:
                break;
            case R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
