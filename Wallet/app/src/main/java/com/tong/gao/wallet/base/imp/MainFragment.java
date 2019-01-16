package com.tong.gao.wallet.base.imp;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.adapter.ConversationListAdapterEx;
import com.tong.gao.wallet.base.BaseFragment;
import com.tong.gao.wallet.base.TabBasePager;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.utils.UIUtils;
import com.tong.gao.wallet.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongContext;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class MainFragment extends BaseFragment implements  RadioGroup.OnCheckedChangeListener {

//    @BindView(R.id.vp_container)
    NoScrollViewPager vpContainer;

    RadioGroup rgFragmentContainer;

    RadioButton rb_fragment_home;
    RadioButton rb_fragment_trade;
    RadioButton rb_fragment_message;
    RadioButton rb_fragment_myInfo;

//    BottomBar   bottomBar;

    private List<Fragment>  pagerList;


    @Override
    public View initView(LayoutInflater inflater,ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);
        vpContainer = (NoScrollViewPager) view.findViewById(R.id.vp_container);
        rgFragmentContainer = (RadioGroup) view.findViewById(R.id.rg_fragment_container);
        rb_fragment_home = view.findViewById(R.id.rb_fragment_home);
        rb_fragment_trade = view.findViewById(R.id.rb_fragment_trade);
        rb_fragment_message = view.findViewById(R.id.rb_fragment_message);
        rb_fragment_myInfo = view.findViewById(R.id.rb_fragment_myInfo);
        fixBottomIcon();


//        ButterKnife.bind(mActivity,view);
        return view;
    }

    private void fixBottomIcon() {
        //定义底部标签图片大小和位置
        Drawable drawable_news = getResources().getDrawable(R.drawable.tab_home_bg);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(0, 0, UIUtils.dip2px(25), UIUtils.dip2px(25));
        //设置图片在文字的哪个方向
        rb_fragment_home.setCompoundDrawables(null, drawable_news, null, null);

        //定义底部标签图片大小和位置
        Drawable drawable_live = getResources().getDrawable(R.drawable.tab_trade_bg);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_live.setBounds(0, 0, UIUtils.dip2px(25), UIUtils.dip2px(25));
        //设置图片在文字的哪个方向
        rb_fragment_trade.setCompoundDrawables(null, drawable_live, null, null);

        //定义底部标签图片大小和位置
        Drawable drawable_tuijian = getResources().getDrawable(R.drawable.tab_message_bg);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_tuijian.setBounds(0, 0, UIUtils.dip2px(25), UIUtils.dip2px(25));
        //设置图片在文字的哪个方向
        rb_fragment_message.setCompoundDrawables(null, drawable_tuijian, null, null);

        //定义底部标签图片大小和位置
        Drawable drawable_me = getResources().getDrawable(R.drawable.tab_myinfo_bg);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_me.setBounds(0, 0, UIUtils.dip2px(30), UIUtils.dip2px(30));
        //设置图片在文字的哪个方向
        rb_fragment_myInfo.setCompoundDrawables(null, drawable_me, null, null);
    }

    @Override
    public void initData() {
        pagerList = new ArrayList<>();

//        pagerList.add(HomePager.newInstance(MyConstant.Tab_1));
        pagerList.add(new HomeFragment());
        pagerList.add(TradePager.newInstance(MyConstant.Tab_2));
        pagerList.add(MessagePager.newInstance(MyConstant.Tab_3));
        pagerList.add(MyInfoPager.newInstance(MyConstant.Tab_2));

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return pagerList.get(position);
            }

            @Override
            public int getCount() {
                return pagerList.size();
            }
        };

        vpContainer.setAdapter(fragmentPagerAdapter);
        vpContainer.setOffscreenPageLimit(4);

        rgFragmentContainer.check(R.id.rb_fragment_home); // 设置默认选中的是home页签
        ((HomeFragment)pagerList.get(0)).initData();
        rgFragmentContainer.setOnCheckedChangeListener(this);

    }




    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int pageIndex = -1;

        switch (checkedId){

            case R.id.rb_fragment_home:
                pageIndex = 0;
                break;

            case R.id.rb_fragment_trade:
                pageIndex = 1;
                break;

            case R.id.rb_fragment_message:
                pageIndex = 2;
                break;

            case R.id.rb_fragment_myInfo:
                pageIndex = 3;
                break;

        }
        vpContainer.setCurrentItem(pageIndex);
//        pagerList.get(pageIndex).initData();
    }





}
