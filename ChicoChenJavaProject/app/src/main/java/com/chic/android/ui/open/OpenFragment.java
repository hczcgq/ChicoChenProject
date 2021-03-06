package com.chic.android.ui.open;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chic.android.R;
import com.chic.android.baen.ClassBean;
import com.chic.android.base.BaseFragment;
import com.chic.android.ui.ItemAdapter;
import com.chic.android.ui.feature.viewpager.ViewPagerActivity;
import com.chic.android.ui.open.lottie.LottieActivity;
import com.chic.android.ui.open.objectbox.ObjectBoxActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

public class OpenFragment extends BaseFragment{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ItemAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void initFragment() {

        mAdapter = new ItemAdapter(R.layout.item_base_list, getDataList());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private List<ClassBean> getDataList() {
        List<ClassBean> list = new ArrayList<>();
        list.add(new ClassBean("Lottie", LottieActivity.class));
        list.add(new ClassBean("ObjectBox数据库", ObjectBoxActivity.class));
        return list;
    }
}
