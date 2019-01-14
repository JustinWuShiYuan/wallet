package com.tong.gao.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.bean.MyConvertCoinOrderBean;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConvertCoinDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.cl_root_view)
    ConstraintLayout clRootView;

    @BindView(R.id.tv_exchange_coin_type)
    TextView tvExchangeCoinType;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_exchanged_num)
    TextView tvExchangedNum;
    @BindView(R.id.tv_get_btc_num)
    TextView tvGetBtcNum;
    @BindView(R.id.tv_get_coin_num)
    TextView tvGetCoinNum;
    @BindView(R.id.tv_order_submit_time)
    TextView tvOrderSubmitTime;
    @BindView(R.id.tv_order_status_exchanged)
    TextView tvOrderStatusExchanged;
    @BindView(R.id.tv_btc_address)
    TextView tvBtcAddress;
    @BindView(R.id.tv_txid)
    TextView tvTxid;
    @BindView(R.id.rl_txid_container)
    RelativeLayout rlTxidContainer;
    @BindView(R.id.tv_reject_info)
    TextView tvRejectInfo;
    @BindView(R.id.rl_reject_container)
    RelativeLayout rlRejectContainer;

    @BindView(R.id.tv_cancel_exchange_or_check_txid)
    TextView    tvCancleExchangeOrCheckTxid;

    private MyConvertCoinOrderBean.MyConvertCoinOrderBeanStatus orderBeanStatus;


    @Override
    protected int getLayout() {
        return R.layout.activity_convert_coin_detail;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2.setText("兑换详情");

        Intent intent = getIntent();
        if(null != intent){
            orderBeanStatus = (MyConvertCoinOrderBean.MyConvertCoinOrderBeanStatus) intent.getSerializableExtra(MyConstant.Exchange_Order_Status);
        }

        switch (orderBeanStatus){

            case Handling:
                tvOrderStatusExchanged.setText("处理中");
                tvOrderStatusExchanged.setTextColor(getResources().getColor(R.color.yellow));

                rlTxidContainer.setVisibility(View.GONE);
                rlRejectContainer.setVisibility(View.GONE);

                tvCancleExchangeOrCheckTxid.setText("撤销兑换");
                break;

            case Rejected:      //驳回
                tvOrderStatusExchanged.setText("已驳回");
                tvOrderStatusExchanged.setTextColor(getResources().getColor(R.color.colorRead));
                rlTxidContainer.setVisibility(View.GONE);
                rlRejectContainer.setVisibility(View.VISIBLE);
                tvCancleExchangeOrCheckTxid.setVisibility(View.GONE);

                break;

            case Remitted:      //已汇出
                tvOrderStatusExchanged.setText("已汇出");
                tvOrderStatusExchanged.setTextColor(getResources().getColor(R.color.colorBlue2));
                rlTxidContainer.setVisibility(View.VISIBLE);
                rlRejectContainer.setVisibility(View.GONE);
                tvCancleExchangeOrCheckTxid.setText("查看Txid");
                break;
        }


        //TODO 加载数据 更新页面数据

        flBack.setOnClickListener(this);
        tvCancleExchangeOrCheckTxid.setOnClickListener(this);

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


            case R.id.tv_cancel_exchange_or_check_txid:

                switch (orderBeanStatus){

                    case Handling:
                        //todo 撤销兑换功能
                        break;


                    case Remitted:      //已汇出
                        //todo 查看Txid功能
                        break;
                }

                break;
        }


    }
}
