package com.tong.gao.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.bean.OrderStatus;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout ivTitleBarMenu2;
    @BindView(R.id.iv_order_status)
    ImageView ivOrderStatus;
    @BindView(R.id.rl_not_willGreenLight)
    RelativeLayout rlNotWillGreenLight;
    @BindView(R.id.iv_complete)
    ImageView ivComplete;
    @BindView(R.id.rl_is_willGreenLight)
    RelativeLayout rlIsWillGreenLight;
    @BindView(R.id.tv_order_detail_num)
    TextView tvOrderDetailNum;
    @BindView(R.id.tv_order_detail_money)
    TextView tvOrderDetailMoney;
    @BindView(R.id.tv_not_willGreenLight)
    TextView tvNotWillGreenLight;

    @BindView(R.id.rl_complete_root)
    RelativeLayout rlCompleteRoot;
    @BindView(R.id.tv_order_remain_time_waiting)
    TextView tvOrderRemainTimeWaiting;
    @BindView(R.id.tv_give_user_hint_info_waiting)
    TextView tvGiveUserHintInfoWaiting;
    @BindView(R.id.rl_waiting_pay)
    RelativeLayout rlWaitingPay;
    @BindView(R.id.tv_order_timeout)
    TextView tvOrderTimeout;
    @BindView(R.id.rl_order_timeout)
    RelativeLayout rlOrderTimeout;
    @BindView(R.id.tv_order_remain_time)
    TextView tvOrderRemainTime;
    @BindView(R.id.tv_second_sure)
    TextView tvSecondSure;
    @BindView(R.id.tv_give_user_hint_info)
    TextView tvGiveUserHintInfo;
    @BindView(R.id.btn_checkout_go)
    Button btnCheckoutGo;
    @BindView(R.id.btn_sold_out)
    Button btnSoldOut;
    @BindView(R.id.rl_will_going)
    RelativeLayout rlWillGoing;
    @BindView(R.id.iv_contact_buyer_icon)
    ImageView ivContactBuyerIcon;
    @BindView(R.id.rl_contact_buyer)
    RelativeLayout rlContactBuyer;

    private OrderStatus orderStatus;


    @Override
    protected int getLayout() {
        return R.layout.activity_my_order_detail;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2.setText("出售AB");
        ivTitleBarMenu2.setOnClickListener(this);
        Intent intent = getIntent();
        if (null != intent) {
             orderStatus = (OrderStatus) intent.getSerializableExtra(MyConstant.MyOrderTypeStatus);
        }

        displayUIByOrderStatus(orderStatus);

    }



    private void displayUIByOrderStatus(OrderStatus orderStatus) {
        switch (orderStatus){
//    rlNotWillGreenLight rlIsWillGreenLight  rlCompleteRoot rlWaitingPay  rlOrderTimeout  rl_will_going
            case Complete:
                ivOrderStatus.setImageDrawable(getDrawable(R.drawable.icon_complete));

                tvNotWillGreenLight.setText("已完成");
                rlNotWillGreenLight.setVisibility(View.VISIBLE);
                rlIsWillGreenLight.setVisibility(View.GONE);
                rlCompleteRoot.setVisibility(View.VISIBLE);
                rlWaitingPay.setVisibility(View.GONE);
                rlOrderTimeout.setVisibility(View.GONE);
                rlWillGoing.setVisibility(View.GONE);
                break;

            case Cancel_TimeOut:
                ivOrderStatus.setImageDrawable(getDrawable(R.drawable.icon_closed));

                tvNotWillGreenLight.setText("订单已超时");

                rlNotWillGreenLight.setVisibility(View.VISIBLE);
                rlIsWillGreenLight.setVisibility(View.GONE);
                rlCompleteRoot.setVisibility(View.GONE);
                rlWaitingPay.setVisibility(View.GONE);
                rlOrderTimeout.setVisibility(View.VISIBLE);

                rlWillGoing.setVisibility(View.GONE);

//                ivContactBuyerIcon.setImageDrawable(getDrawable(R.drawable.icon_appeal));
                rlContactBuyer.setBackgroundColor(getResources().getColor(R.color.gray_5));
                break;

            case Cancel_Buyer:
                ivOrderStatus.setImageDrawable(getDrawable(R.drawable.icon_closed));

                tvNotWillGreenLight.setText("买家已取消");

                rlNotWillGreenLight.setVisibility(View.VISIBLE);
                rlIsWillGreenLight.setVisibility(View.GONE);
                rlCompleteRoot.setVisibility(View.GONE);
                rlWaitingPay.setVisibility(View.GONE);
                rlOrderTimeout.setVisibility(View.VISIBLE);

                rlWillGoing.setVisibility(View.GONE);

                rlContactBuyer.setBackgroundColor(getResources().getColor(R.color.gray_5));
                break;

            case NotPay:
                ivOrderStatus.setImageDrawable(getDrawable(R.drawable.icon_appeal));

                tvNotWillGreenLight.setText("等待对方付款");

                rlWaitingPay.setVisibility(View.VISIBLE);
                rlNotWillGreenLight.setVisibility(View.VISIBLE);
                rlIsWillGreenLight.setVisibility(View.GONE);
                rlCompleteRoot.setVisibility(View.GONE);
                rlOrderTimeout.setVisibility(View.GONE);
                rlWillGoing.setVisibility(View.GONE);





                break;

            case Appealing:             //申诉 页面欠缺


                break;

            case WillGreenLight:        //待放行

                ivOrderStatus.setImageDrawable(getDrawable(R.drawable.icon_complete));

                rlNotWillGreenLight.setVisibility(View.GONE);
                rlIsWillGreenLight.setVisibility(View.VISIBLE);
                rlCompleteRoot.setVisibility(View.GONE);
                rlWaitingPay.setVisibility(View.GONE);
                rlOrderTimeout.setVisibility(View.GONE);
                rlWillGoing.setVisibility(View.VISIBLE);

                btnCheckoutGo.setOnClickListener(this); //确认收款 放行
                btnSoldOut.setOnClickListener(this); //确认收款 放行

                break;
        }
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


            case R.id.btn_checkout_go:  //确认收款放行

                break;


            case R.id.btn_sold_out:  //未收到款，去申诉

                //TODO 到申诉界面

                startActivity(new Intent(MyOrderDetailActivity.this,CommitComplainActivity.class));


                break;


        }
    }
}
