package com.runx.android.ui.home;


import com.runx.android.R;
import com.runx.android.base.BaseFragment;
import com.runx.android.widget.banner.HomeBanner;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HomeChildFragment extends BaseFragment {
    @BindView(R.id.bv_banner)
    HomeBanner bvBanner;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home_recommend;
    }

    @Override
    protected void initFragment() {
        List<String> list = new ArrayList<>();
        list.add("http://img0.imgtn.bdimg.com/it/u=1870389376,1638860294&fm=27&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2996081471,759856654&fm=27&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3189850993,1470820899&fm=27&gp=0.jpg");
        bvBanner.setSource(list).startScroll();
    }
}
