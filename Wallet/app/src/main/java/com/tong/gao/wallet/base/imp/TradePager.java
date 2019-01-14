package com.tong.gao.wallet.base.imp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.activity.SaleCoinActivity;
import com.tong.gao.wallet.base.TabBasePager;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.factory.MiddleViewFactory;
import com.tong.gao.wallet.factory.TradePagerFactory;
import com.tong.gao.wallet.utils.UIUtils;
import com.tong.gao.wallet.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TradePager extends TabBasePager {
    @BindView(R.id.tab_layout_my_trade)
    TabLayout tabLayoutMyTrade;
    @BindView(R.id.vp_my_trade)
    ViewPager   vpMyTrade;


    private boolean isInitView = false;
    private String[]    tabTitles ={"全部","出售中","售罄","已下架"};
    private FragmentActivity fragmentActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            setTabKind(getArguments().getString("tabKindTrade"));
            initView();
        }
        return getRootView();

    }

    public static TradePager newInstance(String tabKind) {
        TradePager fragment = new TradePager();
        Bundle args = new Bundle();
        args.putString("tabKindTrade", tabKind);
        fragment.setArguments(args);
        return fragment;
    }




    private void initView() {
        fragmentActivity = getActivity();
        tvTitle.setText("我的广告");
        ibMenu.setVisibility(View.INVISIBLE);
        ivCreate.setVisibility(View.VISIBLE);
        if (!isInitView) {
            View view = MiddleViewFactory.getMiddleView(MyConstant.Middle_Flcontent_Trade, mContext, R.layout.fragment_trade_pager);
            ButterKnife.bind(this,view);
            if (null != view && null != view.getParent()) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            isInitView = !isInitView;
            flContent2.addView(view);

            initData();
        }


        ivCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SaleCoinActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });
    }


    @Override
    public void initData() {
        setTabTitles(tabTitles);
        MyTabAdapter adapter = new MyTabAdapter(fragmentActivity.getSupportFragmentManager());
        vpMyTrade.setAdapter(adapter);

        //将TabLayout和ViewPager关联起来。
        tabLayoutMyTrade.setupWithViewPager(vpMyTrade);
        //设置可以滑动
        tabLayoutMyTrade.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayoutMyTrade.setSelectedTabIndicatorColor(Color.parseColor("#587BFC"));//设置选中时的指示器的颜色
        tabLayoutMyTrade.setTabIndicatorFullWidth(true);

        vpMyTrade.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int index) {
                BaseFragment fragment = TradePagerFactory.getFragment(index);
                fragment.loadData();
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });


        tabLayoutMyTrade.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(tabLayoutMyTrade.getSelectedTabPosition() == 0 ){
                    BaseFragment fragment = TradePagerFactory.getFragment(tabLayoutMyTrade.getSelectedTabPosition());
                    fragment.loadData();

                    tabLayoutMyTrade.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

    }


}
