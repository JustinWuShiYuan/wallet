package com.tong.gao.wallet.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.factory.PagerFactory;
import com.tong.gao.wallet.factory.TradePagerFactory;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.view.DragView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;


/**
 * 有两种Tab*/
public class TabBasePager extends Fragment {

    //第一个类型的Tab
    public TextView         tvAccount;
    public TextView         tvAccountMoney;
    public TextView         tvAccountChineseMoney;
    public  FrameLayout     flContent;

    //第二个类型的Tab
    public TextView         tvTitle;
    public ImageButton      ibMenu;

    public  FrameLayout     flContent2;
    public  FrameLayout     flContentMessage;
    public  Context         mContext;
    private View            rootViewKind1;
    private View            rootViewKind2;
    private View            rootViewKind3;
    private String          tabKind;    //

    public ImageView         ivCreate;

    protected LinearLayout      llOtherServieContainer;
    protected RelativeLayout    rlOrderInform;
    protected RelativeLayout    rlConnectService;
    protected RelativeLayout    rlSystemInform;



    public void setTabKind(String tabKind) {
        this.tabKind = tabKind;
        initView(tabKind);
    }

    private View initView(String tabKind) {
        mContext = getActivity();
        if(MyConstant.Tab_1 .equals(tabKind)){
            rootViewKind1 = View.inflate(mContext, R.layout.tab_base_pager, null);

            tvAccount = (TextView) rootViewKind1.findViewById(R.id.tv_account);
            tvAccountMoney = (TextView) rootViewKind1.findViewById(R.id.tv_account_money);
            tvAccountChineseMoney = (TextView) rootViewKind1.findViewById(R.id.tv_account_chinese_money);
            flContent = (FrameLayout) rootViewKind1.findViewById(R.id.fl_tab_base_pager_content);

            return rootViewKind1;
        }else if(MyConstant.Tab_2 .equals(tabKind)){
            rootViewKind2 = View.inflate(mContext, R.layout.tab_base_pager2, null);
            tvTitle = (TextView) rootViewKind2.findViewById(R.id.tv_title_bar_title2);
            ibMenu =  rootViewKind2.findViewById(R.id.iv_title_bar_menu2);
            ivCreate = rootViewKind2.findViewById(R.id.iv_create_advertisement);
            flContent2 = (FrameLayout) rootViewKind2.findViewById(R.id.fl_tab_base_pager_content2);

            return rootViewKind2;
        }else if(MyConstant.Tab_3 .equals(tabKind)){

            rootViewKind3 = View.inflate(mContext, R.layout.tab_base_pager3, null);
            tvTitle = (TextView) rootViewKind3.findViewById(R.id.tv_title_bar_title2);
            ibMenu = rootViewKind3.findViewById(R.id.iv_title_bar_menu2);
            ivCreate = rootViewKind3.findViewById(R.id.iv_create_advertisement);

            llOtherServieContainer =  rootViewKind3.findViewById(R.id.ll_other_service);
            rlOrderInform =  rootViewKind3.findViewById(R.id.rl_order_inform);
            rlConnectService =  rootViewKind3.findViewById(R.id.rl_connect_service);
            rlSystemInform =  rootViewKind3.findViewById(R.id.rl_system_inform);

            flContentMessage = (FrameLayout) rootViewKind3.findViewById(R.id.fl_tab_message_pager_content);


            return rootViewKind3;

        }else{


            return null;
        }
    }

    /**
     * 获得当前页面布局对象
     *
     * @return
     */
    public View getRootView() {
        if(MyConstant.Tab_1 .equals(tabKind)){
            return rootViewKind1;
        }else if(MyConstant.Tab_2.equals(tabKind)){
            return rootViewKind2;
        }else {
            return rootViewKind3;
        }

    }

    public void initData() {


    }


    /**
     * 轮训后台数据变更 接口。如果有变化 抛出变化的事件，让具体的子类去
     * 请求对应的接口 刷新页面
     *
     * @return
     */
    private void startRecycleService() {
            //TODO 1.轮训后台变更的接口 2.用EventBus 抛事件
    }



    protected String[] tabTitles;

    public void setTabTitles(String[] tabTitles) {
        this.tabTitles = tabTitles;
    }

    public class MyTabAdapter extends FragmentPagerAdapter {

        public MyTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 通过position去找到对应的fragment
//            Fragment fragment = TradePagerFactory.getFragment(position);

            return TradePagerFactory.getFragment(position);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (tabTitles != null) { return tabTitles[position]; }
            return super.getPageTitle(position);
        }
    }

}
