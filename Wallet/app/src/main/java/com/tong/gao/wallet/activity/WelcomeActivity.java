package com.tong.gao.wallet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.factory.ThreadPoolFactory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class WelcomeActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        ThreadPoolFactory.getScheduledExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                Intent  intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },1,TimeUnit.SECONDS);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello ( String event){
        Toast.makeText( this , event , Toast.LENGTH_SHORT).show();
    }

}
