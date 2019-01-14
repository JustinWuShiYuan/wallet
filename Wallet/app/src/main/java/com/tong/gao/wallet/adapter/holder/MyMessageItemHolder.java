package com.tong.gao.wallet.adapter.holder;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tong.gao.wallet.R;
import com.tong.gao.wallet.bean.MyMessageItemBean;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyMessageItemHolder extends RecyclerView.ViewHolder {
    private CircleImageView             circleImageView;
    private TextView                    tvUserName;
    private TextView                    tvMessageContent;
    private TextView                    tvMessageTime;
    private TextView                    tvMessageNum;

    private Activity                    mActivity;

    private View                        rootView;

    public MyMessageItemHolder(@NonNull View itemView, Activity activity) {
        super(itemView);
        mActivity = activity;
        rootView = itemView;
        initView(itemView);
    }

    public View getRootView() {
        return rootView;
    }

    private void initView(View itemView) {
        circleImageView = itemView.findViewById(R.id.civ_user_head_icon);

        tvUserName = itemView.findViewById(R.id.tv_user_name);

        tvMessageContent = itemView.findViewById(R.id.tv_message_info);

        tvMessageTime = itemView.findViewById(R.id.tv_message_time);

        tvMessageNum = itemView.findViewById(R.id.tv_message_count);

    }

    public void refreshUI(MyMessageItemBean myMessageItemBean) {


        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.head_loading)
                .error(R.drawable.head_loading)
                .fallback(new ColorDrawable(Color.RED));

        Glide.with(mActivity)
                .load("img")
                .apply(requestOptions)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Drawable current = resource.getCurrent();
                        circleImageView.setImageDrawable(current);
                    }
                });

        tvUserName.setText(myMessageItemBean.getUserName());

        tvMessageContent.setText(myMessageItemBean.getMessageContent());

        tvMessageTime.setText(myMessageItemBean.getMessageTime());

        if(!myMessageItemBean.isRead()){
            tvMessageNum.setText(myMessageItemBean.getMessageCount());
        }else{
            tvMessageNum.setVisibility(View.GONE);
        }
    }
}
