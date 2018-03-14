package com.runx.android.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.WindowManager;

import com.flyco.banner.anim.select.ZoomInEnter;
import com.flyco.banner.transform.FadeSlideTransformer;
import com.runx.android.R;
import com.runx.android.base.BaseActivity;
import com.runx.android.common.constant.Constant;
import com.runx.android.common.util.PreferencesUtils;
import com.runx.android.ui.main.MainActivity;
import com.runx.android.widget.banner.GuideBanner;
import java.util.ArrayList;
import butterknife.BindView;

/**
 * Created by 陈国权 on 2018/3/13 0013
 * 引导页
 */

public class GuideActivity extends BaseActivity {
    @BindView(R.id.sgb)
    GuideBanner guideBanner;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initActivity() {
        guideBanner
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(3.5f)
                .setSelectAnimClass(ZoomInEnter.class)
                .setTransformerClass(FadeSlideTransformer.class)
                .barPadding(0, 10, 0, 10)
                .setSource(getGuideImages())
                .startScroll();

        guideBanner.setOnJumpClickL(new GuideBanner.OnJumpClickL() {
            @Override
            public void onJumpClick() {
                PreferencesUtils.putBoolean(GuideActivity.this, Constant.PRE_GUIDE_STATUE, true);
                MainActivity.startActivity(GuideActivity.this);
                finish();
            }
        });
    }

    public ArrayList<Integer> getGuideImages() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.bg_guide_img1);
        list.add(R.drawable.bg_guide_img2);
        list.add(R.drawable.bg_guide_img3);
        list.add(R.drawable.bg_guide_img4);
        return list;
    }
}
