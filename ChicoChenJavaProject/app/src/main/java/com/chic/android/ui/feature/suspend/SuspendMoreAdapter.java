package com.chic.android.ui.feature.suspend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chic.android.R;
import com.chic.android.common.glide.GlideApp;

/**
 * @author: 陈国权
 * @date: 2018/4/13 0013 下午 16:39
 * @description:
 */

public class SuspendMoreAdapter extends RecyclerView.Adapter {

    public static final int TYPE_TIME = 0;
    public static final int TYPE_FEED = 1;

    @Override
    public int getItemViewType(int position) {
        if (position % 4 == 0) {
            return TYPE_TIME;
        }else {
            return TYPE_FEED;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == TYPE_TIME) {
            itemView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suspend_feed, parent, false);
            return new TimeHolder(itemView);
        } else {
            itemView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suspend_time, parent, false);
            return new SuspendAdapter.FeedHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TimeHolder) {
            ((TimeHolder) holder).mTvTime.setText(getTime(position));
        } else if (holder instanceof SuspendAdapter.FeedHolder) {
            GlideApp.with(holder.itemView.getContext())
                    .load(getAvatarResId(position))
                    .centerInside()
                    .fitCenter()
                    .into(((SuspendAdapter.FeedHolder) holder).mIvAvatar);

            GlideApp.with(holder.itemView.getContext())
                    .load(getContentResId(position))
                    .centerInside()
                    .fitCenter()
                    .into(((SuspendAdapter.FeedHolder) holder).mIvContent);

            ((SuspendAdapter.FeedHolder) holder).mTvNickname.setText("Taeyeon " + position);
        }
    }

    private int getAvatarResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;
        }
        return 0;
    }

    private int getContentResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.taeyeon_one;
            case 1:
                return R.drawable.taeyeon_two;
            case 2:
                return R.drawable.taeyeon_three;
            case 3:
                return R.drawable.taeyeon_four;
        }
        return 0;
    }

    private String getTime(int position) {
        return "NOVEMBER " + (1 + position / 4);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static class TimeHolder extends RecyclerView.ViewHolder {
        TextView mTvTime;

        public TimeHolder(View itemView) {
            super(itemView);
            mTvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
