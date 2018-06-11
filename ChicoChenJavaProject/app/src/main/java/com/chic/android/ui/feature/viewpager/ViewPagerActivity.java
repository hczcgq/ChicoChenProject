package com.chic.android.ui.feature.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chic.android.R;
import com.chic.android.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author 陈国权
 * @date 2018/3/14 0014
 */

public class ViewPagerActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.view_page1)
    ViewPager viewPager1;
    @BindView(R.id.view_page2)
    ViewPager viewPager2;
    @BindView(R.id.view_page3)
    ViewPager viewPager3;

    private int[] images = {R.drawable.image0, R.drawable.image1, R.drawable.image2, R.drawable.image3};

    @Override
    protected int getContentViewId() {
        return R.layout.activity_viewpager;
    }

    @Override
    protected void initActivity() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
                | ActionBar.DISPLAY_SHOW_HOME);
        mToolbar.setNavigationIcon(R.drawable.icon_back);
        toolbarTitle.setText("Viewpager动画效果");


        setAdapter(viewPager1);
        setAdapter(viewPager2);
        setAdapter(viewPager3);

        viewPager1.setPageTransformer(true, new AlphaTransformer());
        viewPager2.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager3.setPageTransformer(true, new DepthPageTransformer());
    }

    private void setAdapter(ViewPager viewPager) {
        final List<ImageView> imageViewList = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(images[i]);
            imageViewList.add(imageView);
        }

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imageViewList.get(position));
                return imageViewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageViewList.get(position));
            }
        });
    }
}
