package com.chic.android.ui.open.objectbox;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chic.android.R;

import java.util.List;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

public class UserAdapter extends BaseQuickAdapter<User,BaseViewHolder>{
    public UserAdapter(int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        helper.setText(R.id.tv_name,item.name)
                .setText(R.id.tv_mobile,item.mobile);
    }
}
