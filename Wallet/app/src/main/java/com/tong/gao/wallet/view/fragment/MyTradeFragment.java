package com.tong.gao.wallet.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.activity.SaleCoinActivity;
import com.tong.gao.wallet.adapter.holder.MyTradeItemHolder;
import com.tong.gao.wallet.bean.MyTradeBean;
import com.tong.gao.wallet.bean.TradeStatus;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.utils.UIUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MyTradeFragment extends BaseFragment {
    private List<MyTradeBean> dataList;
//    private MyTradeListAdapter myTradeListAdapter;
    private int myTradeType = 0;
    private String emptyViewType;

    @Override
    protected View onSuccessView() {
        MyTradeListAdapter myTradeListAdapter = new MyTradeListAdapter();
        View view = View.inflate(UIUtils.getContext(),R.layout.my_order_recycleview,null);
        final SwipeRefreshLayout refreshLayout = view.findViewById(R.id.srl_refresh_my_order);
        RecyclerView myOrderRecycleView = view.findViewById(R.id.rv_my_order);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        myOrderRecycleView.setLayoutManager(mLinearLayoutManager);
        myOrderRecycleView.setAdapter(myTradeListAdapter);


        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(UIUtils.getContext(),"刷新数据完成",Toast.LENGTH_LONG).show();
                refreshLayout.setRefreshing(false);
                //TODO 刷新数据
            }
        });


        return refreshLayout;
    }

    @Override
    protected LoadingPager.LoadedResult onLoadData() {
        //TODO 模拟请求网络数据
        Bundle arguments = getArguments();
        if(null != arguments){
            myTradeType = arguments.getInt(MyConstant.MyOrderType);
            emptyViewType = arguments.getString(MyConstant.Empty_ViewType);
        }

        LoadingPager loadingPager = getmPager();
        if(null != loadingPager){
            final ImageView ivEmpty = loadingPager.getIvEmpty();
            final RelativeLayout rlCreateAdvertiseContainer = loadingPager.getRlCreateAdvertiseContainer();
            UIUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(emptyViewType .equals(MyConstant.Empty_ViewTypeOf_Trade)){
                        ivEmpty.setVisibility(View.GONE);
                        rlCreateAdvertiseContainer.setVisibility(View.VISIBLE);
                    }else{
                        ivEmpty.setVisibility(View.VISIBLE);
                        rlCreateAdvertiseContainer.setVisibility(View.GONE);
                    }
                }
            });

        }


        return loadDataByTradeType(myTradeType);

    }

    //空白页面 点击了新建一个广告的回调
    @Override
    protected void executeEmptyTask() {
        UIUtils.getContext().startActivity(new Intent( UIUtils.getContext(),SaleCoinActivity.class));
    }

    private LoadingPager.LoadedResult loadDataByTradeType(int myTradeType) {
        dataList = new ArrayList<>();
        List<TradeStatus.TradePaymentType> paymentTypeList = new ArrayList<>();
        paymentTypeList.add(TradeStatus.TradePaymentType.PaymentZFB);
        paymentTypeList.add(TradeStatus.TradePaymentType.PaymentWechat);
        paymentTypeList.add(TradeStatus.TradePaymentType.PaymentBank);

        for(int i=0;i<20;i++){
            if(i %2 == 0){
                dataList.add(new MyTradeBean("广告ID: 2018222222:"+i, TradeStatus.OnOffering,
                        TradeStatus.TradeType.ConstantDenomination,"100",
                        ""+i,"2222",paymentTypeList,
                        "发布时间:2018-09-12 09:45","最后修改时间: 2018-09-12 09:45"));
            }else if(i %3 == 0) {
                dataList.add(new MyTradeBean("广告ID: 2018222222:"+i, TradeStatus.SellOut,
                        TradeStatus.TradeType.LimitDenomination,"100",
                        ""+i,"2222",paymentTypeList,
                        "发布时间:2018-09-12 09:45","最后修改时间: 2018-09-12 09:45"));
            }else if(i %5 == 0) {
                dataList.add(new MyTradeBean("广告ID: 2018222222:"+i, TradeStatus.SoldOut,
                        TradeStatus.TradeType.LimitDenomination,"100",
                        ""+i,"2222",paymentTypeList,
                        "发布时间:2018-09-12 09:45","最后修改时间: 2018-09-12 09:45"));
            }else{
                dataList.add(new MyTradeBean("广告ID: 2018222222:"+i, TradeStatus.SoldOut,
                        TradeStatus.TradeType.LimitDenomination,"100",
                        ""+i,"2222",paymentTypeList,
                        "发布时间:2018-09-12 09:45","最后修改时间: 2018-09-12 09:45"));
            }


        }
        return LoadingPager.LoadedResult.SUCCESS;

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello ( String event){
        /* Do something */
        Toast.makeText( getContext() , event , Toast.LENGTH_SHORT).show();
    }


    class MyTradeListAdapter extends RecyclerView.Adapter<MyTradeItemHolder> {

        @NonNull
        @Override
        public MyTradeItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            MyTradeItemHolder myViewHolder = new MyTradeItemHolder(
//                    LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_my_trade,viewGroup,false));
            return new MyTradeItemHolder(
                    LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_my_trade,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyTradeItemHolder myOrderItemHolder, int index) {
            myOrderItemHolder.refreshUI(dataList.get(index));
        }

        @Override
        public int getItemCount() {
            LogUtils.d("dataList.size():"+dataList.size());
            return dataList.size();
        }
    }
}
