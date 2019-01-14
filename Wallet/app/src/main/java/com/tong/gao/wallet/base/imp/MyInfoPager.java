package com.tong.gao.wallet.base.imp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tong.gao.wallet.R;
import com.tong.gao.wallet.activity.AboutUsActivity;
import com.tong.gao.wallet.activity.MyAssetActivity;
import com.tong.gao.wallet.activity.MyOrderActivity;
import com.tong.gao.wallet.activity.MyPersonInfoActivity;
import com.tong.gao.wallet.activity.MyReceiptAccountActivity;
import com.tong.gao.wallet.activity.SafeSettingActivity;
import com.tong.gao.wallet.activity.SaleCoinActivity;
import com.tong.gao.wallet.base.TabBasePager;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.factory.MiddleViewFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfoPager extends TabBasePager implements View.OnClickListener {
    @BindView(R.id.rl_my_info)
    RelativeLayout rlMyInfo;
    @BindView(R.id.civ_user_head_src)
    CircleImageView civUserHeadSrc;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;

    @BindView(R.id.rl_my_property)
    RelativeLayout rlMyProperty;
    @BindView(R.id.rl_my_account)
    RelativeLayout rlMyAccount;
    @BindView(R.id.rl_my_orders)
    RelativeLayout rlMyOrders;
    @BindView(R.id.rl_my_advertising)
    RelativeLayout rlMyAdvertising;
    @BindView(R.id.rl_safe_setting)
    RelativeLayout rlSafeSetting;
    @BindView(R.id.rl_about_us)
    RelativeLayout rlAboutUs;

    Unbinder unbinder;
    private boolean isInitView = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            setTabKind(getArguments().getString("tabKindMyInfo"));
            initView();
        }
        unbinder = ButterKnife.bind(this, getRootView());
        return getRootView();

    }


    public static MyInfoPager newInstance(String tabKind) {
        MyInfoPager fragment = new MyInfoPager();
        Bundle args = new Bundle();
        args.putString("tabKindMyInfo", tabKind);
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        tvTitle.setText("我的");
        ibMenu.setVisibility(View.INVISIBLE);

        if (!isInitView) {
            View view = MiddleViewFactory.getMiddleView(MyConstant.Middle_Flcontent_MyInfo, mContext, R.layout.fragment_my_info);
            ButterKnife.bind(this, view);
            if (null != view && null != view.getParent()) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            isInitView = !isInitView;
            flContent2.addView(view);

            rlMyInfo.setOnClickListener(this);
            rlMyProperty.setOnClickListener(this);
            rlMyAccount.setOnClickListener(this);
            rlMyOrders.setOnClickListener(this);
            rlMyAdvertising.setOnClickListener(this);
            rlSafeSetting.setOnClickListener(this);
            rlAboutUs.setOnClickListener(this);

            initData();
        }

    }

    @Override
    public void initData() {
        //TODO 获取网络数据 缓存到本地
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.head_loading)
                .error(R.drawable.head_loading)
                .fallback(new ColorDrawable(Color.RED));

        Glide.with(getActivity())
                .load("http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg")
                .apply(requestOptions)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Drawable current = resource.getCurrent();
                        civUserHeadSrc.setImageDrawable(current);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.rl_my_info:       //我的个人信息
                startActivity(new Intent(getActivity(),MyPersonInfoActivity.class));

                break;

            case R.id.rl_my_property:       //我的资产
                startActivity(new Intent(getActivity(),MyAssetActivity.class));

                break;

            case R.id.rl_my_account:       //我的收款账号
                startActivity(new Intent(getActivity(),MyReceiptAccountActivity.class));

                break;


            case R.id.rl_my_orders:       //我的订单
                startActivity(new Intent(getActivity(),MyOrderActivity.class));
                break;


            case R.id.rl_my_advertising:       //我的广告
                startActivity(new Intent(getActivity(),SaleCoinActivity.class));
                break;


            case R.id.rl_safe_setting:       //安全设置
                startActivity(new Intent(getActivity(),SafeSettingActivity.class));
                break;


            case R.id.rl_about_us:       //关于我们
                startActivity(new Intent(getActivity(),AboutUsActivity.class));
                break;

        }

    }
}
