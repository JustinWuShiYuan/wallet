package com.tong.gao.wallet.activity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeAdvertisementActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;

    @BindView(R.id.fl_back)
    FrameLayout ivBackHome;

    @BindView(R.id.cb_height_authentication)
    CheckBox cbHeightAuthentication;
    @BindView(R.id.cb_no_trade_other_platform)
    CheckBox cbNoTradeOtherPlatform;
    @BindView(R.id.btn_change_advertisement)
    Button btnChangeAdvertisement;
    @BindView(R.id.btn_sold_out)
    Button btnSoldOut;

    @Override
    protected int getLayout() {
        return R.layout.activity_change_advertisement;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2 = findViewById(R.id.tv_title_bar_title2);
        tvTitleBarTitle2.setText("修改广告");
        ivBackHome = findViewById(R.id.fl_back);
        ivBackHome.setOnClickListener(this);

        cbHeightAuthentication = findViewById(R.id.cb_height_authentication);
        cbNoTradeOtherPlatform = findViewById(R.id.cb_no_trade_other_platform);

        btnChangeAdvertisement = findViewById(R.id.btn_change_advertisement);
        btnChangeAdvertisement.setOnClickListener(this);
        btnSoldOut = findViewById(R.id.btn_sold_out);
        btnSoldOut.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.fl_back:
                this.finish();
                break;

            case R.id.cb_height_authentication:

                break;
            case R.id.cb_no_trade_other_platform:

                break;
            case R.id.btn_change_advertisement: //修改广告
                checkIsAgreeProtocol();
                break;

            case R.id.btn_sold_out:     //下架
                    //TODO  确认要下架广告吗？确认 取消  下架之后直接返回广告列表首页
                break;


        }
    }


    private void checkIsAgreeProtocol() {
        if(cbHeightAuthentication.isChecked() && cbNoTradeOtherPlatform.isChecked()){
            Toast.makeText(this,"下个页面",Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra(MyConstant.CoinLimit,MyConstant.CoinLimit);
            intent.setClass(this,SaleCoinActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,getString(R.string.pleaseCheckProtocol),Toast.LENGTH_LONG).show();
        }
    }
}
