package com.tong.gao.wallet.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tong.gao.wallet.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConversationListActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.cl_root_view)
    ConstraintLayout clRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);
        ButterKnife.bind(this);

        initData();

        flBack.setOnClickListener(this);
    }

    private void initData() {
        tvTitleBarTitle2.setText("消息列表");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.fl_back:
                this.finish();
                break;

        }
    }
}
