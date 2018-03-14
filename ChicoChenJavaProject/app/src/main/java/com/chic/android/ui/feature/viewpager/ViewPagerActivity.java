package com.chic.android.ui.feature.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chic.android.R;
import com.chic.android.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

public class ViewPagerActivity extends BaseActivity{

    @BindView(R.id.view_page)
    ViewPager viewPager;

    private List<ImageView> imageViewList = new ArrayList<>();

    private int[] images = {R.drawable.image0, R.drawable.image1, R.drawable.image2, R.drawable.image3};

    @Override
    protected int getContentViewId() {
        return R.layout.activity_viewpager;
    }

    @Override
    protected void initActivity() {
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
        viewPager.setPageTransformer(true,new DepthPageTransformer());
    }
}
