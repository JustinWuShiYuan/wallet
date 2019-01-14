package com.tong.gao.wallet.adapter.holder;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.bean.MyMessageItemBean;
import com.tong.gao.wallet.utils.StringUtils;

import de.hdodenhof.circleimageview.CircleImageView;


public class MySystemItemHolder extends RecyclerView.ViewHolder {
    private CircleImageView             circleImageView;
//    private ImageView                   circleImageView;
    private TextView                    tvUserName;
    private TextView                    tvMessageContent;
    private TextView                    tvMessageTime;
    private TextView                    tvMessageNum;

    private Activity                    mActivity;

    public MySystemItemHolder(@NonNull View itemView, Activity activity) {
        super(itemView);
        mActivity = activity;
        initView(itemView);
    }

    private void initView(View itemView) {
        circleImageView = itemView.findViewById(R.id.civ_user_head_icon);

        tvUserName = itemView.findViewById(R.id.tv_user_name);

        tvMessageContent = itemView.findViewById(R.id.tv_message_info);

        tvMessageTime = itemView.findViewById(R.id.tv_message_time);

        tvMessageNum = itemView.findViewById(R.id.tv_message_count);

    }

    public void refreshUI(MyMessageItemBean myMessageItemBean) {
        circleImageView.setImageResource(R.drawable.img_logo_head);

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