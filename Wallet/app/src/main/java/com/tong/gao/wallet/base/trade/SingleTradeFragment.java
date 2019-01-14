package com.tong.gao.wallet.base.trade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.base.BaseFragment;
import com.tong.gao.wallet.constants.MyConstant;

public class SingleTradeFragment extends BaseFragment {
    public static String TAB_LAYOUT_FRAGMENT = "tab_fragment";
    private int type;
    private RelativeLayout  rlTradeLimit;   //单笔限额
    private RelativeLayout  rlTradeConstant;//单笔固额

    private TextView    tvMinLimitValue;
    private TextView    tvMaxLimitValue;
    //TODO 单笔固定金额

    private TextView    tvSaleNums;         //卖出的数量
    private TextView    tvPaymentTime;      //付款时间期限

    public static SingleTradeFragment newInstance(int type) {
        SingleTradeFragment fragment = new SingleTradeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAB_LAYOUT_FRAGMENT, type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_single_trade, container, false);
        tvMinLimitValue = view.findViewById(R.id.tv_min_limit);
        tvMaxLimitValue = view.findViewById(R.id.tv_max_limit);

        tvSaleNums = view.findViewById(R.id.tv_sale_nums);
        tvPaymentTime = view.findViewById(R.id.tv_payment_time_value);

        rlTradeLimit = view.findViewById(R.id.rl_trade_limit);
        rlTradeConstant = view.findViewById(R.id.rl_trade_constant);

//        tv_payment_time_value
        initData();
        return view;
    }

    @Override
    public void initData() {
        switch (type){

            case MyConstant.singleLimitTrade:       //单笔限额交易
                rlTradeConstant.setVisibility(View.GONE);
                rlTradeLimit.setVisibility(View.VISIBLE);
                break;

            case MyConstant.singleConstantTrade:       //单笔GU额交易
                rlTradeConstant.setVisibility(View.VISIBLE);
                rlTradeLimit.setVisibility(View.GONE);
                break;

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TAB_LAYOUT_FRAGMENT);
        }

    }
}
