package com.tong.gao.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.bean.MyConvertCoinOrderBean;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.utils.StringUtils;
import com.tong.gao.wallet.utils.UIUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConvertCoinActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_exchange_rate)
    TextView tvExchangeRate;
    @BindView(R.id.et_input_coin_num)
    EditText etInputCoinNum;
    @BindView(R.id.et_get_coin_num)
    EditText etGetCoinNum;

    @BindView(R.id.et_input_wallet_address)
    EditText etInputWalletAddress;
    @BindView(R.id.tv_submit_apply)
    TextView tvSubmitApply;
    @BindView(R.id.rv_my_apply)
    RecyclerView rvMyApply;

    private List<MyConvertCoinOrderBean> dataList;

    @Override
    protected int getLayout() {
        return R.layout.activity_convert_coin;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2.setText("兑换比特币");
        flBack.setOnClickListener(this);
        etInputCoinNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(null != s && !StringUtils.isEmpty(s.toString())){
                    int inputValue = Integer.parseInt(s.toString());
                    LogUtils.d("inputValue:"+inputValue);
                    etGetCoinNum.setText((inputValue*0.5)+"");
                }

            }
        });

        etGetCoinNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etInputWalletAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        tvSubmitApply.setOnClickListener(this);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        rvMyApply.setLayoutManager(mLinearLayoutManager);

        loadData();
    }

    private void loadData() {
        dataList = new ArrayList<>();
        for(int i =0;i<10;i++){
            if(i % 3 == 0){
                dataList.add(new MyConvertCoinOrderBean("orderid"+i,"1546 AB = 0.7BTC "+i,MyConvertCoinOrderBean.MyConvertCoinOrderBeanStatus.Handling));
            }else if(i % 2 == 0){
                dataList.add(new MyConvertCoinOrderBean("orderid"+i,"1546 AB = 0.7BTC "+i,MyConvertCoinOrderBean.MyConvertCoinOrderBeanStatus.Remitted));
            }else {
                dataList.add(new MyConvertCoinOrderBean("orderid"+i,"1546 AB = 0.7BTC "+i,MyConvertCoinOrderBean.MyConvertCoinOrderBeanStatus.Rejected));
            }
        }


        rvMyApply.setAdapter(new MyConvertCoinAdapter());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(String event) {
        Toast.makeText(this, event, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.fl_back:
                this.finish();
                break;


            case R.id.tv_submit_apply:      //提交申请

                break;


        }
    }



    class MyConvertCoinAdapter extends RecyclerView.Adapter<MyConvertCoinAdapter.MyConvertCoinItemHolder> {

        @NonNull
        @Override
        public MyConvertCoinItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            MyConvertCoinItemHolder myViewHolder = new MyConvertCoinItemHolder(
//                    LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_convert_coin,viewGroup,false));
            return new MyConvertCoinItemHolder(
                    LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_convert_coin,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyConvertCoinItemHolder myOrderItemHolder, int index) {
            myOrderItemHolder.refreshUI(dataList.get(index));
        }

        @Override
        public int getItemCount() {
            LogUtils.d("dataList.size():"+dataList.size());
            return dataList.size();
        }

        class MyConvertCoinItemHolder extends RecyclerView.ViewHolder{
            RelativeLayout rlToDetailPage;
            TextView coinAB2BTC;

            public MyConvertCoinItemHolder(@NonNull View itemView) {
                super(itemView);
                coinAB2BTC = itemView.findViewById(R.id.tv_ab_to_btc);
                rlToDetailPage = itemView.findViewById(R.id.rl_to_detail_page);
            }

            public void refreshUI(final MyConvertCoinOrderBean myConvertCoinOrderBean) {
                coinAB2BTC.setText(myConvertCoinOrderBean.getConvertResult());
                rlToDetailPage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(MyConstant.Exchange_Order_Status,myConvertCoinOrderBean.getOrderStatus());
                        intent.setClass(ConvertCoinActivity.this,ConvertCoinDetailActivity.class);
                        ConvertCoinActivity.this.startActivity(intent);
//                        Toast.makeText(ConvertCoinActivity.this,""+myConvertCoinOrderBean.getConvertOrderId(),Toast.LENGTH_LONG).show();
                    }
                });
            }

        }
    }
}
