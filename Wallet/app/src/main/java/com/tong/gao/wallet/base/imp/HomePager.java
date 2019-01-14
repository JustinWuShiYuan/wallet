package com.tong.gao.wallet.base.imp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.activity.ConvertCoinActivity;
import com.tong.gao.wallet.activity.DataCountActivity;
import com.tong.gao.wallet.activity.MyOrderActivity;
import com.tong.gao.wallet.activity.SaleCoinActivity;
import com.tong.gao.wallet.base.TabBasePager;
import com.tong.gao.wallet.bean.PendingOrderBean;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.event.HomePagerEvent;
import com.tong.gao.wallet.factory.MiddleViewFactory;
import com.tong.gao.wallet.factory.ThreadPoolFactory;
import com.tong.gao.wallet.utils.TimeUtils;
import com.tong.gao.wallet.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class HomePager extends TabBasePager implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    TextView            tvTodayIncomeMoney;
    TextView            tvYesterdayIncomeMoney;
    TextView            tvTodayMonthMoney;
    TextView            tvLastMonthIncomeMoney;
    RelativeLayout      rlSaleCoinContainer;
    RelativeLayout      rlMyOrderContainer;
    RelativeLayout      rlDataCountContainer;
    RelativeLayout      rlExchangeCoinContainer;
//    @BindView(R.id.rv_root_view)
    RecyclerView        recyclerView;

    private SwipeRefreshLayout refreshLayout;

    private List<PendingOrderBean> pendingOrderBeanList = new ArrayList<>();
    private boolean isInitView = false;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            setTabKind(getArguments().getString("tabKindHome"));
            initView();
        }
        EventBus.getDefault().register(this);
        return getRootView();

    }

    public static HomePager newInstance(String tabKind) {
        HomePager fragment = new HomePager();
        Bundle args = new Bundle();
        args.putString("tabKindHome", tabKind);
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        if(!isInitView){
            tvAccount.setText("测试账户");
            tvAccountMoney.setText("2000 AB");
            tvAccountChineseMoney.setText("折合人民币 1000.00");

            View view = MiddleViewFactory.getMiddleView(MyConstant.Middle_Flcontent_HomePager,mContext,R.layout.fragment_home_pager);
            if(null != view && null != view.getParent()){
                ((ViewGroup)view.getParent()).removeView(view);
            }

            initContentHomePagerView(view);
            isInitView = !isInitView;
            flContent.addView(view);

            initData();
        }

    }

    @Override
    public void initData() {
        //模拟网路请求 获取待处理订单的数据
        getPendingOrderDataFromServer(0);
    }

    private void getPendingOrderDataFromServer(final int startIndex) {
        ThreadPoolFactory.getScheduledExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                //NetWorks.TODO SOMETING

//                pendingOrderBeanList.clear();
                for(int i =startIndex;i<20+startIndex;i++){
                    pendingOrderBeanList.add(new PendingOrderBean("2018-12-17 12:12:01",
                            100000,"Justin"+i,i+1+""));
                }

                //更新待处理订单UI布局
                updatePendingOrderUI();
            }
        },100,TimeUnit.MILLISECONDS);
    }


    private boolean isStart = false;
    private void updatePendingOrderUI() {
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                recyclerView.setAdapter(new MyPendingOrderAdapter());
                //TODO 刷新其他数据
            }
        });

        if(!isStart){
            startUpdateAllItemsCountdownUI();
        }
    }



    private void initContentHomePagerView(View view) {
        refreshLayout =view.findViewById(R.id.srl_refresh_root_view);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);

        refreshLayout.setOnRefreshListener(this);

        tvTodayIncomeMoney = view.findViewById(R.id.tv_today_income_money);

        tvYesterdayIncomeMoney = view.findViewById(R.id.tv_yesterday_income_money);

        tvTodayMonthMoney = view.findViewById(R.id.tv_today_month_money);

        tvLastMonthIncomeMoney = view.findViewById(R.id.tv_last_month_income_money);

        rlSaleCoinContainer = view.findViewById(R.id.rl_sale_coin_container);
        rlSaleCoinContainer.setOnClickListener(this);

        rlMyOrderContainer = view.findViewById(R.id.rl_my_order_container);
        rlMyOrderContainer.setOnClickListener(this);

        rlDataCountContainer = view.findViewById(R.id.rl_data_count_container);
        rlDataCountContainer.setOnClickListener(this);

        rlExchangeCoinContainer = view.findViewById(R.id.rl_exchange_coin_container);
        rlExchangeCoinContainer.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.rv_root_view);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setHasFixedSize(true);

    }

    //TODO 收到变更的服务通知，请求数据 刷新页面
    public void getDataChangeNotify() {

    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND  , sticky =  true )
    public void onEventHomePager(HomePagerEvent event) {
        getPendingOrderDataFromServer((int)(Math.random()*10+1));
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.rl_sale_coin_container:       //卖币
                mContext.startActivity(new Intent(mContext,SaleCoinActivity.class));
                break;

            case R.id.rl_my_order_container:       //我的订单

                mContext.startActivity(new Intent(mContext,MyOrderActivity.class));

                break;

            case R.id.rl_data_count_container:       //数据统计
                mContext.startActivity(new Intent(mContext,DataCountActivity.class));

                break;

            case R.id.rl_exchange_coin_container:    //兑换比特币
                mContext.startActivity(new Intent(mContext,ConvertCoinActivity.class));
                break;

        }
    }

    @Override
    public void onRefresh() {
        EventBus.getDefault().post(new HomePagerEvent());
    }


    public class MyPendingOrderAdapter extends RecyclerView.Adapter<MyPendingOrderAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            return new MyViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.home_pager_item_order,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int index) {
            myViewHolder.tvOrderTime.setText(pendingOrderBeanList.get(index).getOrderTime());
            myViewHolder.tvOrderCountdownTime.setText(TimeUtils.transferLongToDate(pendingOrderBeanList.get(index).getCountDownTime()));
            myViewHolder.tvUserName.setText(pendingOrderBeanList.get(index).getOrderUserName());
            myViewHolder.tvBuyCoinNum.setText(pendingOrderBeanList.get(index).getUserBuyCoinNum());
            myViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"点击了第"+index+"个条目",Toast.LENGTH_LONG).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return pendingOrderBeanList.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder{
            ConstraintLayout rootView;
            TextView tvOrderTime;
            TextView tvOrderCountdownTime;
            TextView tvUserName;
            TextView tvBuyCoinNum;
            public MyViewHolder(View itemView) {
                super(itemView);
                rootView = itemView.findViewById(R.id.cl_pending_order_container);
                tvOrderTime = (TextView) itemView.findViewById(R.id.tv_order_time);
                tvOrderCountdownTime = (TextView) itemView.findViewById(R.id.tv_order_count_down_time);
                tvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
                tvBuyCoinNum = (TextView) itemView.findViewById(R.id.tv_buy_coin_num);
            }
        }

    }


    /**
     *TODO 2件事
     * 1，更新数据集合中每个元素的倒计时 时间。
     * 2. 刷新展示在屏幕区域条目的ui
     * */

    private UpdateCountdownTimeRunnable updateCountdownTimeRunnable;
    private void startUpdateAllItemsCountdownUI() {
        isStart = true;
        if(null ==  updateCountdownTimeRunnable){
            updateCountdownTimeRunnable = new UpdateCountdownTimeRunnable();
        }

        ScheduledFuture<?> scheduledFuture = ThreadPoolFactory.getScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int j = 0;
                for (int i = 0; i < pendingOrderBeanList.size(); i++) {
                    if (pendingOrderBeanList.get(i).getCountDownTime() - 1000 >= 0) {
                        pendingOrderBeanList.get(i).setCountDownTime(pendingOrderBeanList.get(i).getCountDownTime() - 1000);
                    } else {
                        j++;
                    }
                }
                if (j < pendingOrderBeanList.size()) {
                    UIUtils.runOnUiThread(updateCountdownTimeRunnable);
                }
            }
        }, 1, 1, TimeUnit.SECONDS);

    }


    class UpdateCountdownTimeRunnable implements Runnable {

        @Override
        public void run() {
            UIUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            });

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
