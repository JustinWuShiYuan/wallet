package com.tong.gao.wallet.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.adapter.holder.MyOrderItemHolder;
import com.tong.gao.wallet.bean.MyOrderBean;
import com.tong.gao.wallet.bean.OrderStatus;
import com.tong.gao.wallet.bean.PaymentWay;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.utils.UIUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

public class MyOrderFragment extends BaseFragment {
    private List<MyOrderBean> dataList;
//    private MyOrderListAdapter myOrderListAdapter;
    private int myOrderType = 0;



    @Override
    protected View onSuccessView() {
        MyOrderListAdapter myOrderListAdapter = new MyOrderListAdapter();

        View view = View.inflate(UIUtils.getContext(),R.layout.my_order_recycleview,null);
        final SwipeRefreshLayout refreshLayout = view.findViewById(R.id.srl_refresh_my_order);
        RecyclerView myOrderRecycleView = view.findViewById(R.id.rv_my_order);


        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        myOrderRecycleView.setLayoutManager(mLinearLayoutManager);

        myOrderRecycleView.setAdapter(myOrderListAdapter);

        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(UIUtils.getContext(),"2",Toast.LENGTH_LONG).show();

                refreshLayout.setRefreshing(false);

                //TODO 刷新数据
            }
        });



        return refreshLayout;

    }


    @Override
    protected LoadingPager.LoadedResult onLoadData() {
        LogUtils.d("onLoadData() ........myOrderType:."+myOrderType);
        Bundle arguments = getArguments();
        if(null != arguments){
            myOrderType = arguments.getInt(MyConstant.MyOrderType);
        }

        if(myOrderType ==  OrderStatus.ALL.getState()){
            loadDataByOrderType();
        }else {
            loadDataByOrderType(myOrderType);
        }


        return LoadingPager.LoadedResult.SUCCESS;
    }

    //空白页面
    @Override
    protected void executeEmptyTask() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello ( String event){
        /* Do something */
        Toast.makeText( getContext() , event , Toast.LENGTH_SHORT).show();
    }


    private void loadDataByOrderType() {
        dataList = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            if(i == 0){
                dataList.add(new MyOrderBean(OrderStatus.Complete,"Jusint1:"+ i,
                        "200", PaymentWay.Wechat,"3000",
                        "201812-"+i,"20181223"));
            }

            if(i == 1){
                dataList.add(new MyOrderBean( OrderStatus.Cancel_TimeOut,"Jusint1:"+ i,
                        "200", PaymentWay.ZFB,"3000",
                        "201812-"+i,"20181223"));
            }

            if(i == 2){
                dataList.add(new MyOrderBean( OrderStatus.Appealing,"Jusint1:"+ i,
                        "200", PaymentWay.ZFB,"3000",
                        "201812-"+i,"20181223"));
            }

            if(i == 3){
                dataList.add(new MyOrderBean( OrderStatus.NotPay,"Jusint1:"+ i,
                        "200", PaymentWay.ZFB,"3000",
                        "201812-"+i,"20181223"));
            }

            if(i == 4){
                dataList.add(new MyOrderBean( OrderStatus.WillGreenLight,"Jusint1:"+ i,
                        "200", PaymentWay.Bank,"3000",
                        "201812-"+i,"20181223"));
            }
            dataList.add(new MyOrderBean( OrderStatus.WillGreenLight,"Jusint1:"+ i,
                    "200", PaymentWay.Bank,"3000",
                    "201812-"+i,"20181223"));

        }
    }
    private void loadDataByOrderType(int orderType) {
        dataList = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            if( OrderStatus.Complete.getState() == orderType){
                dataList.add(new MyOrderBean( OrderStatus.Complete,"Jusint1",
                        "200", PaymentWay.Bank,"3000",
                        "201812-"+i,"20181223"));
            }else if( OrderStatus.Cancel_TimeOut.getState() == orderType){
                dataList.add(new MyOrderBean( OrderStatus.Cancel_TimeOut,"Jusint1",
                        "200", PaymentWay.ZFB,"3000",
                        "201812-"+i,"20181223"));
                if(i %2 == 0){
                    dataList.add(new MyOrderBean( OrderStatus.Cancel_Buyer,"Jusint1",
                            "200", PaymentWay.Wechat,"3000",
                            "201812-"+i,"20181223"));
                }

            }else if( OrderStatus.WillGreenLight.getState() == orderType){
                dataList.add(new MyOrderBean( OrderStatus.WillGreenLight,"Jusint1",
                        "200", PaymentWay.ZFB,"3000",
                        "201812-"+i,"20181223"));
            }else if( OrderStatus.NotPay.getState() == orderType){
                dataList.add(new MyOrderBean( OrderStatus.NotPay,"Jusint1",
                        "200", PaymentWay.Wechat,"3000",
                        "201812-"+i,"20181223"));
            }else if( OrderStatus.Appealing.getState() == orderType){
                dataList.add(new MyOrderBean( OrderStatus.Appealing,"Jusint1",
                        "200", PaymentWay.Wechat,"3000",
                        "201812-"+i,"20181223"));
            }

        }
    }



    class MyOrderListAdapter extends RecyclerView.Adapter<MyOrderItemHolder> {

        @NonNull
        @Override
        public MyOrderItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            return new MyOrderItemHolder(
                    LayoutInflater.from(getActivity()).inflate(R.layout.item_my_order,viewGroup,false),getActivity());
        }

        @Override
        public void onBindViewHolder(@NonNull MyOrderItemHolder myOrderItemHolder, int index) {
            myOrderItemHolder.refreshUI(dataList.get(index));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

}
