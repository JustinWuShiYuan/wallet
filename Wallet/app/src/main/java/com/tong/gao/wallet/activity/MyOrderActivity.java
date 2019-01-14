package com.tong.gao.wallet.activity;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.factory.PagerFactory;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.view.LineChartFragment;
import com.tong.gao.wallet.view.fragment.BaseFragment;
import com.tong.gao.wallet.view.fragment.LoadingPager;
import com.tong.gao.wallet.view.fragment.MyOrderFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout ivTitleBarMenu2;
    @BindView(R.id.tab_layout_my_order)
    TabLayout tabLayoutMyOrder;
    @BindView(R.id.vp_my_order)
    ViewPager vpMyOrder;


    private String[]    tabTitles ={"全部","未付款","待放行","申诉中","已取消","已完成"};


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(String event) {
        Toast.makeText(this, event, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2 = findViewById(R.id.tv_title_bar_title2);
        tvTitleBarTitle2.setText("我的订单");
        ivTitleBarMenu2 = findViewById(R.id.fl_back);
        ivTitleBarMenu2.setOnClickListener(this);

        setTabTitles(tabTitles);
        tabLayoutMyOrder = findViewById(R.id.tab_layout_my_order);
        vpMyOrder = findViewById(R.id.vp_my_order);


        MyTabAdapter adapter = new MyTabAdapter(getSupportFragmentManager());
        //给ViewPager设置适配器
        vpMyOrder.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tabLayoutMyOrder.setupWithViewPager(vpMyOrder);
        //设置可以滑动
        tabLayoutMyOrder.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayoutMyOrder.setSelectedTabIndicatorColor(Color.parseColor("#587BFC"));//设置选中时的指示器的颜色


        vpMyOrder.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int index) {
                BaseFragment fragment = PagerFactory.getFragment(index);
                fragment.loadData();
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        tabLayoutMyOrder.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(tabLayoutMyOrder.getSelectedTabPosition() == 0 ){
                    BaseFragment fragment = PagerFactory.getFragment(tabLayoutMyOrder.getSelectedTabPosition());
                    fragment.loadData();

                    tabLayoutMyOrder.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_title_bar_title2:

                break;


            case R.id.fl_back:

                this.finish();

                break;



        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
