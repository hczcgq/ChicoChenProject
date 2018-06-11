package com.chic.android.ui.feature.cardview;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.chic.android.R;
import com.chic.android.base.BaseActivity;
import com.chic.android.common.DensityUtils;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * @author: 陈国权
 * @date: 2018/6/11 0011 下午 15:18
 * @description:
 */

public class ViewPagerCardViewActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.btn_fragment_view)
    Button btn;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_viewpager_cardview;
    }

    @Override
    protected void initActivity() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
                | ActionBar.DISPLAY_SHOW_HOME);
        mToolbar.setNavigationIcon(R.drawable.icon_back);
        toolbarTitle.setText("Viewpager+CardView");

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
                DensityUtils.dp2px(this, 2));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }


    @OnClick(R.id.btn_fragment_view)
    public void onViewClicked() {
        if (!mShowingFragments) {
            btn.setText("Views");
            mViewPager.setAdapter(mFragmentCardAdapter);
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        } else {
            btn.setText("Fragments");
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
        }
        mShowingFragments = !mShowingFragments;
    }

    @OnCheckedChanged(R.id.checkbox)
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
    }
}
