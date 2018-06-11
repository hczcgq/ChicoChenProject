package com.chic.android.ui.open.objectbox;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
    private Box<User> boxStore;

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


        if (mAdapter == null) {
            mAdapter = new UserAdapter(R.layout.item_user, getUser());
            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                User user = (User) adapter.getData().get(position);
                AddUserActivity.startActivity(ObjectBoxActivity.this, user.id, 100);
            }
        });
    }

    private List<User> getUser() {
        if (boxStore == null) {
            boxStore = AppApplication.getInstance().getBoxStore().boxFor(User.class);
        }
        List<User> userList = boxStore.getAll();
        return userList;
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
                AddUserActivity.startActivity(this, -1, 100);
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 100) {
            mAdapter.setNewData(getUser());
        }
    }
}
