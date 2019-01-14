package com.tong.gao.wallet.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PublishAdvertisementActivity extends BaseActivity implements View.OnClickListener {

    TextView    tvTitle;
    FrameLayout ivBackHome;
    Button btnBackHome;
    Button btnCheckAdv;

    @Override
    protected int getLayout() {
        return R.layout.activity_publish_advertisement;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tv_title_bar_title2);
        tvTitle.setText("发布广告");
        ivBackHome = findViewById(R.id.fl_back);
        btnBackHome = findViewById(R.id.btn_sold_out);
        btnCheckAdv = findViewById(R.id.btn_change_advertisement);
        ivBackHome.setOnClickListener(this);
        btnBackHome.setOnClickListener(this);
        btnCheckAdv.setOnClickListener(this);

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

            case R.id.btn_sold_out:
                 this.finish();
                break;

            case R.id.btn_change_advertisement:
                 startActivity(new Intent(this,ChangeAdvertisementActivity.class));
                 this.finish();
                break;
        }
    }
}
