package com.chic.android.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chic.android.R;
import com.chic.android.baen.ClassBean;

import java.util.List;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

public class ItemAdapter extends BaseQuickAdapter<ClassBean, BaseViewHolder>{

    public ItemAdapter(int layoutResId, @Nullable List<ClassBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ClassBean item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, item.getClassName());
                mContext.startActivity(intent);
            }
        });
    }
}
