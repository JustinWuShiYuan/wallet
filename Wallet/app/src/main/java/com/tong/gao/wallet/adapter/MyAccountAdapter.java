package com.tong.gao.wallet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.tong.gao.wallet.R;
import com.tong.gao.wallet.bean.MyReceiptAccountItemBean;

import java.util.List;


public class MyAccountAdapter extends BaseAdapter<MyReceiptAccountItemBean, MyAccountAdapter.ViewHolder> {


    private List<MyReceiptAccountItemBean> mDataList;

    public MyAccountAdapter(Context context) {
        super(context);
    }

    @Override
    public void notifyDataSetChanged(List<MyReceiptAccountItemBean> dataList) {
        this.mDataList = dataList;
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(getInflater().inflate(R.layout.item_receipt_account, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }




     class ViewHolder extends RecyclerView.ViewHolder {
        SwitchButton switchButtonZfb;
        ImageView ivPaymentIcon;
        TextView tvPaymentType;
        TextView tvAccountNum;
        public ViewHolder(View itemView) {
            super(itemView);
            switchButtonZfb = itemView.findViewById(R.id.switch_button_zfb);
            ivPaymentIcon = itemView.findViewById(R.id.iv_payment_icon);
            tvPaymentType = itemView.findViewById(R.id.tv_payment_type);
            tvAccountNum = itemView.findViewById(R.id.tv_account_num);
        }

        public void setData(MyReceiptAccountItemBean accountItemBean) {

            switchButtonZfb.setChecked(accountItemBean.isOpen());

            switch (accountItemBean.getGetMoneyType()){

                case ZFB:
                    ivPaymentIcon.setImageResource(R.drawable.icon_zfb);
                    tvPaymentType.setText("支付宝");
                    break;

                case Bank:
                    ivPaymentIcon.setImageResource(R.drawable.icon_bank);
                    tvPaymentType.setText("银行");
                    break;

                case Wechat:
                    ivPaymentIcon.setImageResource(R.drawable.icon_wechat);
                    tvPaymentType.setText("微信");
                    break;
            }

            tvAccountNum.setText(accountItemBean.getAccountNum());

        }
    }
}
