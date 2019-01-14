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
import com.tong.gao.wallet.adapter.holder.MySystemItemHolder;
import com.tong.gao.wallet.bean.MyMessageItemBean;
import com.tong.gao.wallet.utils.UIUtils;

import java.util.LinkedList;
import java.util.List;

public class MySystemFragment extends BaseFragment {

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



    private void loadDataByOrderType() {
        dataList = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            if(i %2 == 0){
                dataList.add(new MyMessageItemBean( null,"Jusint"+i,
                        "messageContent", "2018-02-06 12:32",true,"1"+i));
            }else{
                dataList.add(new MyMessageItemBean( null,"Jusint"+i,
                        "messageContent", "2018-02-06 12:32",false,"1"+i));
            }


        }
    }


    class MyMessageListAdapter extends RecyclerView.Adapter<MySystemItemHolder> {

        @NonNull
        @Override
        public MySystemItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            return new MySystemItemHolder(
                    LayoutInflater.from(getActivity()).inflate(R.layout.item_my_message,viewGroup,false),getActivity());
        }

        @Override
        public void onBindViewHolder(@NonNull MySystemItemHolder myMessageItemHolder, int index) {
            myMessageItemHolder.refreshUI(dataList.get(index));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}
