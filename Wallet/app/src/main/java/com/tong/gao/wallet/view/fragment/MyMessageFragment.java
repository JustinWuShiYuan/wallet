package com.tong.gao.wallet.view.fragment;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.adapter.holder.MyMessageItemHolder;
import com.tong.gao.wallet.bean.MyMessageItemBean;
import com.tong.gao.wallet.utils.UIUtils;

import java.util.LinkedList;
import java.util.List;

public class MyMessageFragment extends BaseFragment {

    private List<MyMessageItemBean> dataList;



    @Override
    protected View onSuccessView() {
        MyMessageListAdapter myOrderListAdapter = new MyMessageListAdapter();

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
        loadDataByOrderType();
        return LoadingPager.LoadedResult.SUCCESS;
    }

    @Override
    protected void executeEmptyTask() {

    }



    String headUrl ="http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";
    String headUrl2 ="https://ad.12306.cn/res/delivery/0002/2017/09/04/201709041647021598.jpg";

    private void loadDataByOrderType() {
        dataList = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            if(i %2 == 0){
                dataList.add(new MyMessageItemBean( headUrl,"Jusint"+i,
                        "messageContent", "2018-02-06 12:32",true,"1"+i));
            }else{
                dataList.add(new MyMessageItemBean( headUrl2,"Jusint"+i,
                        "messageContent", "2018-02-06 12:32",false,"1"+i));
            }


        }
    }


    class MyMessageListAdapter extends RecyclerView.Adapter<MyMessageItemHolder> {

        @NonNull
        @Override
        public MyMessageItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int position) {
            MyMessageItemHolder myMessageItemHolder = new MyMessageItemHolder(
                    LayoutInflater.from(getActivity()).inflate(R.layout.item_my_message, viewGroup, false), getActivity());

            return myMessageItemHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyMessageItemHolder myMessageItemHolder, final int index) {
            myMessageItemHolder.refreshUI(dataList.get(index));
            myMessageItemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"第"+index,Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}
