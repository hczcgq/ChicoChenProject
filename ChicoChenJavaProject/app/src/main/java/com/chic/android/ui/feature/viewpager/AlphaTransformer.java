package com.chic.android.ui.feature.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by 陈国权 on 2018/3/12 0012
 */

public class AlphaTransformer implements ViewPager.PageTransformer {
    private float MINALPHA = 0.1f;

    @Override
    public void transformPage(View page, float position) {
        if(position<-1||position>1){
            page.setAlpha(MINALPHA);
        }else{
            if(position>0){
                page.setAlpha(MINALPHA+(1-position)*(1-MINALPHA));
            }else{
                page.setAlpha(MINALPHA + (1 + position) * (1 - MINALPHA));
            }
        }
    }
}
