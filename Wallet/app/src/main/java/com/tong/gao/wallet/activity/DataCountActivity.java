package com.tong.gao.wallet.activity;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.view.LineChartFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class DataCountActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvTitle;
    private FrameLayout ivBackIcon;

    private TabLayout   tabLayoutIncome;
    private ViewPager   viewPagerIncome;
    private TextView    tvTotalIncome;

    private TabLayout   tabLayoutTotalOrder;
    private ViewPager   viewPagerTotalOrder;
    private TextView    tvTotalOrderNum;

    public static final String[] tabTitles = new String[]{"7天", "30天", "90天"};
    private List<Fragment> totalIncomeFragments = new ArrayList<>();
    private List<Fragment> totalOrdersFragments = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.activity_data_count;
    }

    @Override
    protected void initView() {
        setTabTitles(tabTitles);

        tvTitle = findViewById(R.id.tv_title_bar_title2);
        tvTitle.setText("数据统计");
        ivBackIcon = findViewById(R.id.fl_back);
        ivBackIcon.setOnClickListener(this);

        initIncomeView();
        loadIncomeData();

        initTotalOrderView();
        loadTotalOrderData();


    }

    private void loadTotalOrderData() {
        //TODO 请求后台获取 数据
        for (int i = 0; i < tabTitles.length; i++) {
            totalOrdersFragments.add(LineChartFragment.newInstance(i + 1));
        }
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), totalOrdersFragments);
        //给ViewPager设置适配器
        viewPagerTotalOrder.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tabLayoutTotalOrder.setupWithViewPager(viewPagerTotalOrder);
        //设置可以滑动
        tabLayoutTotalOrder.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initTotalOrderView() {
        tvTotalOrderNum = findViewById(R.id.tv_total_order_num);
        tabLayoutTotalOrder = findViewById(R.id.tab_layout_total_order);
        tabLayoutIncome.setSelectedTabIndicatorColor(Color.parseColor("#587BFC"));//设置选中时的指示器的颜色
        tabLayoutIncome.setTabMode(TabLayout.MODE_SCROLLABLE);//可滑动，默认是FIXED
        viewPagerTotalOrder = findViewById(R.id.view_pager_total_order);
    }

    private void initIncomeView() {
        tvTotalIncome = findViewById(R.id.tv_total_income_money);
        tabLayoutIncome = findViewById(R.id.tab_layout_total_income);
//        tabLayoutIncome.setTabTextColors(Color.GRAY,Color.parseColor("#587BFC"));//设置文本在选中和为选中时候的颜色
        tabLayoutIncome.setSelectedTabIndicatorColor(Color.parseColor("#587BFC"));//设置选中时的指示器的颜色
        tabLayoutIncome.setTabMode(TabLayout.MODE_SCROLLABLE);//可滑动，默认是FIXED
        viewPagerIncome = findViewById(R.id.view_pager_total_income);
    }

    private void loadIncomeData() {
        //TODO 请求后台获取 数据
        for (int i = 0; i < tabTitles.length; i++) {
            totalIncomeFragments.add(LineChartFragment.newInstance(i + 1));
        }
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), totalIncomeFragments);
        //给ViewPager设置适配器
        viewPagerIncome.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tabLayoutIncome.setupWithViewPager(viewPagerIncome);
        //设置可以滑动
        tabLayoutIncome.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello ( String event){
        Toast.makeText( this , event , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }




}



