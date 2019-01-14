package com.tong.gao.wallet.adapter.holder;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.activity.MyOrderDetailActivity;
import com.tong.gao.wallet.bean.MyOrderBean;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.interfaces.DialogCallBack;
import com.tong.gao.wallet.utils.DialogUtils;
import com.tong.gao.wallet.utils.UIUtils;

import static com.tong.gao.wallet.bean.OrderStatus.WillGreenLight;


/**
 */
public class MyOrderItemHolder extends RecyclerView.ViewHolder {

    TextView tvOrderStatus;
    TextView tvBuyerName;
    TextView tvToSaleCoinNum;
    ImageView ivPaymentWay;
    TextView tvShouldGetMoney;
    TextView tvOrderTime;
    TextView tvOrderCode;
    TextView tvHandleOrder;
    RelativeLayout rlHandleOrderRootView;

    private Activity mActivity;

    public MyOrderItemHolder(@NonNull View itemView, Activity activity) {
        super(itemView);
        mActivity = activity;
        initView(itemView);
    }


    protected View initView(View itemView) {

        tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
        tvBuyerName = itemView.findViewById(R.id.tv_buyer_name);
        tvToSaleCoinNum = itemView.findViewById(R.id.tv_to_sale_coin_num);
        ivPaymentWay =  itemView.findViewById(R.id.iv_payment_way);
        tvShouldGetMoney =  itemView.findViewById(R.id.tv_should_get_money);
        tvOrderTime =  itemView.findViewById(R.id.tv_order_time);
        tvOrderCode =  itemView.findViewById(R.id.tv_order_code);

        rlHandleOrderRootView =  itemView.findViewById(R.id.rl_handle_order_root_view);
        tvHandleOrder =  itemView.findViewById(R.id.tv_handle_order);

        return itemView;
    }



    AlertDialog.Builder builder;
    AlertDialog dialogLogin;

    public void refreshUI(final MyOrderBean data) {
        if(null != data ){

            switch (data.getOrderStatus()){

                case Complete:
                    tvOrderStatus.setText("已完成");
                    tvOrderStatus.setTextColor(UIUtils.getColor(R.color.gray_8));
                    rlHandleOrderRootView.setVisibility(View.GONE);
                    break;

                case Cancel_TimeOut:
                    tvOrderStatus.setText("取消");
                    tvOrderStatus.setTextColor(UIUtils.getColor(R.color.gray_8));
                    rlHandleOrderRootView.setVisibility(View.GONE);
                    break;

                case Cancel_Buyer:
                    tvOrderStatus.setText("取消");
                    tvOrderStatus.setTextColor(UIUtils.getColor(R.color.gray_8));
                    rlHandleOrderRootView.setVisibility(View.GONE);
                    break;

                case WillGreenLight:
                    tvOrderStatus.setText("待放行");
                    tvOrderStatus.setTextColor(UIUtils.getColor(R.color.colorBlue));
                    rlHandleOrderRootView.setVisibility(View.VISIBLE);
                    tvHandleOrder.setBackground(UIUtils.getContext().getDrawable(R.drawable.shape_blue1_bg));
                    tvHandleOrder.setText("放行订单");
                    tvHandleOrder.setTextColor(UIUtils.getColor(R.color.colorBlue));
                    break;

                case NotPay:
                    tvOrderStatus.setText("等待买方付款");
                    tvOrderStatus.setTextColor(UIUtils.getColor(R.color.colorRead));
                    rlHandleOrderRootView.setVisibility(View.VISIBLE);
                    tvHandleOrder.setBackground(UIUtils.getContext().getDrawable(R.drawable.shape_red_bg));
                    tvHandleOrder.setText("提醒付款");
                    tvHandleOrder.setTextColor(UIUtils.getColor(R.color.colorRead));
                    break;

                case Appealing:
                    tvOrderStatus.setText("申诉中");
                    tvOrderStatus.setTextColor(UIUtils.getColor(R.color.yellow));
                    rlHandleOrderRootView.setVisibility(View.VISIBLE);
                    tvHandleOrder.setBackground(UIUtils.getContext().getDrawable(R.drawable.shape_yellow_bg));
                    tvHandleOrder.setText("联系买家");
                    tvHandleOrder.setTextColor(UIUtils.getColor(R.color.yellow));
                    break;

            }

            tvOrderStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(UIUtils.getContext(),"data.getOrderStatus():"+(data.getOrderStatus()),Toast.LENGTH_LONG).show();
//                    Context context = UIUtils.getContext();
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(MyConstant.MyOrderTypeStatus,data.getOrderStatus());
                    intent.setClass(mActivity,MyOrderDetailActivity.class);
                    mActivity.startActivity(intent);
                }
            });


            tvHandleOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (data.getOrderStatus().equals(WillGreenLight)) {//待放行

                        DialogUtils.createAlertDialog(mActivity, R.layout.dialog_permit_through, new DialogCallBack() {
                            @Override
                            public void cancel(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void sure(Dialog dialog) {
                                Toast.makeText(mActivity,"确认",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });


                    }
                }
            });


            tvBuyerName.setText(data.getBuyerName());
            tvToSaleCoinNum.setText(data.getBuyCoinNum());

            switch (data.getPaymentType()){
                case ZFB:
                    ivPaymentWay.setImageDrawable(UIUtils.getContext().getDrawable(R.drawable.icon_cir_zfb));
                    break;

                case Wechat:
                    ivPaymentWay.setImageDrawable(UIUtils.getContext().getDrawable(R.drawable.icon_cir_wechat));
                    break;

                case Bank:
                    ivPaymentWay.setImageDrawable(UIUtils.getContext().getDrawable(R.drawable.icon_cir_bank));
                    break;
            }
            tvShouldGetMoney.setText(data.getShouldGetMoney());
            tvOrderTime.setText(data.getOrderTime());
            tvOrderCode.setText(data.getOrderCode());

        }
    }


//    /**
//     * 弹出AlertDialog
//     */
//    private void createAlertDialog() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity,R.style.Dialog);
//        View v = LayoutInflater.from(mActivity).inflate(R.layout.dialog_permit_through, null);
//        final Dialog dialog = builder.create();
//        dialog.show();
//
//        Window window = dialog.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//
//        lp.width = DensityUtil.dp2px(mActivity, 300);
//        lp.height = DensityUtil.dp2px(mActivity, 350);
//        window.setGravity(Gravity.CENTER);
//        window.setAttributes(lp);
//        window.setContentView(v);
//    }

}
