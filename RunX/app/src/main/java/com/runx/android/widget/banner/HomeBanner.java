package com.runx.android.widget.banner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.banner.widget.Banner.BaseIndicatorBanner;
import com.runx.android.R;
import com.runx.android.common.glide.ImageLoad;

/**
 * Created by 陈国权 on 2018/3/9 0009.
 */

public class HomeBanner extends BaseIndicatorBanner<String, HomeBanner> {

    private ColorDrawable colorDrawable;

    public HomeBanner(Context context) {
        this(context, null, 0);
    }

    public HomeBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        colorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }

    @Override
    public void onTitleSlect(TextView textView, int position) {
    }

    @Override
    public View onCreateItemView(int position) {
        View inflate = View.inflate(mContext, R.layout.item_home_recommend_banner, null);
        ImageView bannerImage = (ImageView) inflate.findViewById(R.id.iv_banner);

        if (!TextUtils.isEmpty(mDatas.get(position))) {
            ImageLoad.loadImage(mContext, mDatas.get(position), bannerImage);
        } else {
            bannerImage.setImageDrawable(colorDrawable);
        }

        return inflate;
    }
}
