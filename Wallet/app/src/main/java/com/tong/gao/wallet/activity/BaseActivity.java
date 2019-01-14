package com.tong.gao.wallet.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.tong.gao.wallet.factory.PagerFactory;
import com.tong.gao.wallet.utils.Density;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Raul_lsj on 2018/3/9.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Activity mActivity;
    protected Context appContext;
    protected Application mApplication;
    protected String[] tabTitles;
    protected Intent    myIntent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        appContext = getApplicationContext();
        mApplication = getApplication();
        setOrientation();
        setContentView(getLayout());
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        myIntent = getIntent();
        initView();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    public void setOrientation() {
        Density.setDefault(mActivity);
    }

    protected abstract int getLayout();

    protected abstract void initView();


    public class TabAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;


        public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        //设置tablayout标题
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];

        }
    }

    public void setTabTitles(String[] tabTitles) {
        this.tabTitles = tabTitles;
    }

    public class MyTabAdapter extends FragmentPagerAdapter{

        public MyTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 通过position去找到对应的fragment
//            Fragment fragment = PagerFactory.getFragment(position);

            return PagerFactory.getFragment(position);
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
