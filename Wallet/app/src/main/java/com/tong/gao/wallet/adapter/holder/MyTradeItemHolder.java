package com.tong.gao.wallet.adapter.holder;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.bean.MyTradeBean;
import com.tong.gao.wallet.bean.TradeStatus;
import com.tong.gao.wallet.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 */
public class MyTradeItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_trade_id)
    TextView tvTradeId;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_trade_type)
    TextView tvTradeType;
    @BindView(R.id.tv_remain_num)
    TextView tvRemainNum;
    @BindView(R.id.tv_sales_count)
    TextView tvSalesCount;
    @BindView(R.id.iv_cir_bank)
    ImageView ivCirBank;
    @BindView(R.id.iv_cir_wechat)
    ImageView ivCirWechat;
    @BindView(R.id.iv_cir_zfb)
    ImageView ivCirZfb;
    @BindView(R.id.tv_publish_time)
    TextView tvPublishTime;
    @BindView(R.id.tv_last_change_time)
    TextView tvLastChangeTime;
    @BindView(R.id.tv_sold_out)
    TextView tvSoldOut;
    @BindView(R.id.tv_sold_in)
    TextView tvSoldIn;
    @BindView(R.id.tv_compile)
    TextView tvCompile;

    private MyTradeBean myTradeBean;

    public MyTradeItemHolder(@NonNull View itemView) {
        super(itemView);
//        ButterKnife.bind(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        tvTradeId = itemView.findViewById(R.id.tv_trade_id);
        tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
        tvTradeType = itemView.findViewById(R.id.tv_trade_type);
        tvRemainNum = itemView.findViewById(R.id.tv_remain_num);
        tvSalesCount = itemView.findViewById(R.id.tv_sales_count);
        ivCirBank = itemView.findViewById(R.id.iv_cir_bank);
        ivCirWechat = itemView.findViewById(R.id.iv_cir_wechat);
        ivCirZfb = itemView.findViewById(R.id.iv_cir_zfb);
        tvPublishTime = itemView.findViewById(R.id.tv_publish_time);
        tvLastChangeTime = itemView.findViewById(R.id.tv_last_change_time);
        tvSoldOut = itemView.findViewById(R.id.tv_sold_out);
        tvSoldIn = itemView.findViewById(R.id.tv_sold_in);
        tvCompile = itemView.findViewById(R.id.tv_compile);
    }


    public void refreshUI(final MyTradeBean data) {
        if (null != data) {
            myTradeBean = data;
            tvTradeId.setText(data.getTradeId());
            switch (data.getTradeStatus()) {
                case OnOffering:
                    tvOrderStatus.setText("上架中");
                    break;

                case SellOut:
                    tvOrderStatus.setText("售罄");
                    break;

                case SoldOut:
                    tvOrderStatus.setText("已下架");
                    break;

            }



            switch (data.getTradeType()){//是限额 还是固额

                case ConstantDenomination:
                    tvTradeType.setText("类型：固额"+data.getTradeTypeMoney());
                    tvRemainNum.setText("库存:"+data.getRepertoryNum());
                    tvSalesCount.setText("销量:"+data.getSellNum());
                    break;


                case LimitDenomination:
                    tvTradeType.setText("类型：限额"+data.getTradeTypeMoney());
                    tvRemainNum.setText("剩余总数:"+data.getRepertoryNum());
                    tvSalesCount.setText("已售总数:"+data.getSellNum());
                    break;
            }


            //支付方式
            List<TradeStatus.TradePaymentType> paymentTypeList = data.getPaymentTypeList();
            for(int i =0;i<paymentTypeList.size();i++){
                if(paymentTypeList.get(i) == TradeStatus.TradePaymentType.PaymentZFB){
                    ivCirZfb.setVisibility(View.VISIBLE);
                }
                if(paymentTypeList.get(i) == TradeStatus.TradePaymentType.PaymentWechat){
                    ivCirWechat.setVisibility(View.VISIBLE);
                }
                if(paymentTypeList.get(i) == TradeStatus.TradePaymentType.PaymentBank){
                    ivCirBank.setVisibility(View.VISIBLE);
                }
            }

            tvPublishTime.setText(data.getPublishTime());

            tvLastChangeTime.setText(data.getLastChangeTime());

            tvCompile.setOnClickListener(this);
            tvSoldIn.setOnClickListener(this);
            tvSoldOut.setOnClickListener(this);


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_compile:       //编辑
                Toast.makeText(UIUtils.getContext(),"第"+myTradeBean.getRepertoryNum()+" 个编辑",Toast.LENGTH_LONG).show();
                break;

            case R.id.tv_sold_in:       //上架
                Toast.makeText(UIUtils.getContext(),"第"+myTradeBean.getRepertoryNum()+" 个上架",Toast.LENGTH_LONG).show();
                break;

            case R.id.tv_sold_out:       //下架
                Toast.makeText(UIUtils.getContext(),"第"+myTradeBean.getRepertoryNum()+" 个下架",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
