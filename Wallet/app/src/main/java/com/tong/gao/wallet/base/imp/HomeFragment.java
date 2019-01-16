package com.tong.gao.wallet.base.imp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.activity.ConvertCoinActivity;
import com.tong.gao.wallet.activity.DataCountActivity;
import com.tong.gao.wallet.activity.MyOrderActivity;
import com.tong.gao.wallet.activity.SaleCoinActivity;
import com.tong.gao.wallet.bean.PendingOrderBean;
import com.tong.gao.wallet.factory.ThreadPoolFactory;
import com.tong.gao.wallet.utils.TimeUtils;
import com.tong.gao.wallet.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private Context mContext;
    private int scrollState;
    RecyclerView recyclerView;
    LinearLayout rlSaleCoinContainer;
    LinearLayout rlMyOrderContainer;
    LinearLayout rlDataCountContainer;
    LinearLayout rlExchangeCoinContainer;
    private List<PendingOrderBean> pendingOrderBeanList = new ArrayList<>();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.fragment_home_pager_new, null);
        rlSaleCoinContainer = rootView.findViewById(R.id.rl_sale_coin_container);
        rlSaleCoinContainer.setOnClickListener(this);

        rlMyOrderContainer = rootView.findViewById(R.id.rl_my_order_container);
        rlMyOrderContainer.setOnClickListener(this);

        rlDataCountContainer = rootView.findViewById(R.id.rl_data_count_container);
        rlDataCountContainer.setOnClickListener(this);

        rlExchangeCoinContainer = rootView.findViewById(R.id.rl_exchange_coin_container);
        rlExchangeCoinContainer.setOnClickListener(this);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView = rootView.findViewById(R.id.rv_root_view);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                Log.e("HomeFragment", String.format("newState: %s", newState));
                scrollState = newState;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        initData();
        return rootView;
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_sale_coin_container:       //卖币
                mContext.startActivity(new Intent(mContext, SaleCoinActivity.class));
                break;

            case R.id.rl_my_order_container:       //我的订单

                mContext.startActivity(new Intent(mContext, MyOrderActivity.class));

                break;

            case R.id.rl_data_count_container:       //数据统计
                mContext.startActivity(new Intent(mContext, DataCountActivity.class));

                break;

            case R.id.rl_exchange_coin_container:    //兑换比特币
                mContext.startActivity(new Intent(mContext, ConvertCoinActivity.class));
                break;

        }
    }


    public class MyPendingOrderAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_pager_item_order, viewGroup, false));
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
                    Toast.makeText(mContext, "点击了第" + index + "个条目", Toast.LENGTH_LONG).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return pendingOrderBeanList.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            recyclerView.setAdapter(new MyPendingOrderAdapter());
        }
    };


    public void initData() {
        //模拟网路请求 获取待处理订单的数据
        getPendingOrderDataFromServer(0);
//        createFakeData();
        mHandler.sendMessageDelayed(mHandler.obtainMessage(), 1000);
    }

    private void createFakeData() {
        for (int i = 0; i < 1000; i++) {
            PendingOrderBean pendingOrderBean = new PendingOrderBean("2018-12-17 12:12:01",
                    100000, "Justin", 1 + "");
            pendingOrderBeanList.add(pendingOrderBean);
        }
    }

    private void getPendingOrderDataFromServer(final int startIndex) {
        ThreadPoolFactory.getScheduledExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                //NetWorks.TODO SOMETING

//                pendingOrderBeanList.clear();
                for (int i = startIndex; i < 20 + startIndex; i++) {
                    pendingOrderBeanList.add(new PendingOrderBean("2018-12-17 12:12:01",
                            100000, "Justin" + i, i + 1 + ""));
                }

                //更新待处理订单UI布局
                updatePendingOrderUI();
            }
        }, 100, TimeUnit.MILLISECONDS);
    }

    private boolean isStart = false;

    private void updatePendingOrderUI() {
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new MyPendingOrderAdapter());
                //TODO 刷新其他数据
            }
        });

        if (!isStart) {
            startUpdateAllItemsCountdownUI();
        }
    }

    private UpdateCountdownTimeRunnable updateCountdownTimeRunnable;

    private void startUpdateAllItemsCountdownUI() {
        isStart = true;
        if (null == updateCountdownTimeRunnable) {
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
                    if (scrollState == 0) {
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }
            });

        }
    }

}
