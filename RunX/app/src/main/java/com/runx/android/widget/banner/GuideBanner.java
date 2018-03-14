package com.runx.android.widget.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.flyco.banner.widget.Banner.BaseIndicatorBanner;
import com.runx.android.R;
import com.runx.android.common.glide.ImageLoad;


/**
 * Created by 陈国权 on 2018/3/13 0013
 */

public class GuideBanner extends BaseIndicatorBanner<Integer, GuideBanner> {

    private ImageView ivImage;
    private TextView tvJump;

    public GuideBanner(Context context) {
        this(context, null, 0);
    }

    public GuideBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuideBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBarShowWhenLast(false);
    }

    @Override
    public View onCreateItemView(int position) {
        View inflate = View.inflate(mContext, R.layout.item_guide, null);
        ivImage = inflate.findViewById(R.id.iv_image);
        tvJump = inflate.findViewById(R.id.tv_jump);

        final Integer resId = mDatas.get(position);
        tvJump.setVisibility(position == mDatas.size() - 1 ? VISIBLE : GONE);
        ImageLoad.loadImage(mContext, resId, ivImage);

        tvJump.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onJumpClickL != null)
                    onJumpClickL.onJumpClick();
            }
        });

        return inflate;
    }

    private OnJumpClickL onJumpClickL;

    public interface OnJumpClickL {
        void onJumpClick();
    }

    public void setOnJumpClickL(OnJumpClickL onJumpClickL) {
        this.onJumpClickL = onJumpClickL;
    }
}
