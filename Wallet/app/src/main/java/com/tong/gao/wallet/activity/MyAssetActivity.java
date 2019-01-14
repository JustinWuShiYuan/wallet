package com.tong.gao.wallet.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.base.imp.HomePager;
import com.tong.gao.wallet.bean.MyAssetItemBean;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;
import com.tong.gao.wallet.utils.TimeUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAssetActivity extends BaseActivity {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout ivTitleBarMenu2;
    @BindView(R.id.tv_my_coin)
    TextView tvMyCoin;
    @BindView(R.id.tv_my_money)
    TextView tvMyMoney;
    @BindView(R.id.tv_can_used_money)
    TextView tvCanUsedMoney;
    @BindView(R.id.tv_margin_money)
    TextView tvMarginMoney;
    @BindView(R.id.tv_trading_money)
    TextView tvTradingMoney;
    @BindView(R.id.rv_trade_list)
    RecyclerView rvTradeList;


    private MyAssetTradAdapter myAssetTradAdapter;
    private List<MyAssetItemBean> dataList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_asset);
        ButterKnife.bind(this);
        initView();
        loadData();
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello ( String event){
        Toast.makeText( this , event , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_my_asset;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2.setText("我的资产");
        ivTitleBarMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        rvTradeList.setLayoutManager(mLinearLayoutManager);
    }

    private void loadData() {
        if(null == dataList){
            dataList = new ArrayList<>();
        }
        //TODO 模拟请求数据
        for(int i=0;i< 20;i++){
            if(i % 2== 0){
                dataList.add(new MyAssetItemBean(""+i,"兑换比特币"+i,"2018.21.21 12:30:30","100 AB",true));
            }else{
                dataList.add(new MyAssetItemBean(""+i,"买入"+i,"2018.21.21 12:30:30","100 AB",false));
            }

        }

        if(null ==  myAssetTradAdapter ){
            myAssetTradAdapter = new MyAssetTradAdapter();
            rvTradeList.setAdapter(myAssetTradAdapter);
        }

    }



    public class MyAssetTradAdapter extends RecyclerView.Adapter<MyAssetTradAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyAssetTradAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            return new MyAssetTradAdapter.MyViewHolder(
                    LayoutInflater.from(MyAssetActivity.this).inflate(R.layout.item_my_asset,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull final MyAssetTradAdapter.MyViewHolder myViewHolder,  int index) {
            myViewHolder.updateUI(dataList.get(index));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tvTradeTitle;
            TextView tvTradeTime;
            TextView tvTradeCoinNum;
            public MyViewHolder(View itemView) {
                super(itemView);
                tvTradeTitle = (TextView) itemView.findViewById(R.id.tv_trade_title);
                tvTradeTime = (TextView) itemView.findViewById(R.id.tv_trade_time);
                tvTradeCoinNum = (TextView) itemView.findViewById(R.id.tv_trade_coin_num);
            }


            public void updateUI(MyAssetItemBean myAssetItemBean) {
                tvTradeTitle.setText(myAssetItemBean.getAssetTradeTitle());
                tvTradeTime.setText(myAssetItemBean.getTradeTime());
                tvTradeCoinNum.setText(myAssetItemBean.getTradeCoinNum());
            }
        }

    }
}
