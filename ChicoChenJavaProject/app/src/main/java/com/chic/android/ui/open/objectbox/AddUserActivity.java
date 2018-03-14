package com.chic.android.ui.open.objectbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chic.android.AppApplication;
import com.chic.android.R;
import com.chic.android.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.objectbox.Box;
import io.objectbox.query.Query;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

public class AddUserActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_sex)
    EditText etSex;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.btn_save)
    Button btnSave;


    private Long id;
    private Box<User> boxStore;
    private User tempUser;

    public static void startActivity(Activity context, long id, int requestCode) {
        Intent intent = new Intent(context, AddUserActivity.class);
        intent.putExtra("id", id);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void getIntentData() {
        id = getIntent().getExtras().getLong("id");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_objectbox_add;
    }

    @Override
    protected void initActivity() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
                | ActionBar.DISPLAY_SHOW_HOME);
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbarTitle.setText(id == -1 ? R.string.save : R.string.update);

        btnSave.setText(id == -1 ? R.string.save : R.string.update);


        boxStore = AppApplication.getInstance().getBoxStore().boxFor(User.class);

        if (id != -1) {
            Query<User> query = boxStore.query().equal(User_.id, id).build();
            tempUser = query.findFirst();
            if (tempUser != null) {
                etNickname.setText(tempUser.name);
                etMobile.setText(tempUser.mobile);
                etSex.setText(tempUser.sex);
                etAge.setText(tempUser.age);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (id != -1) {
            getMenuInflater().inflate(R.menu.menu_delete, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.item_delete:
                boxStore.remove(id);
                setResult(RESULT_OK);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        long result = -1;
        String name = etNickname.getText().toString();
        String mobile = etMobile.getText().toString();
        String sex = etSex.getText().toString();
        String age = etAge.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile)) {
            return;
        }

        if (id == -1) {
            User user = new User(0, name, mobile, age, sex);
            result = boxStore.put(user);
        } else {
            tempUser.name = name;
            tempUser.mobile = mobile;
            tempUser.sex = sex;
            tempUser.age = age;
            result = boxStore.put(tempUser);
        }

        if (result != -1) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
