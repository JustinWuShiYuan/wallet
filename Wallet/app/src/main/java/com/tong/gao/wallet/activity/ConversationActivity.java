package com.tong.gao.wallet.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tong.gao.wallet.R;

public class ConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        String sName = getIntent().getData().getQueryParameter("targetId");//获取昵称
        setTitle("与" + sName + "聊天中");
    }
}
