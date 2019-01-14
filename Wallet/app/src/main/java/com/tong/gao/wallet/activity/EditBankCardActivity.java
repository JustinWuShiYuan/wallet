package com.tong.gao.wallet.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditBankCardActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout ivTitleBarMenu2;
    @BindView(R.id.et_input_name)
    EditText etInputName;
    @BindView(R.id.et_input_bank_type)
    EditText etInputBankType;
    @BindView(R.id.et_input_bank_type_branch)
    EditText etInputBankTypeBranch;
    @BindView(R.id.et_input_bank_num)
    EditText etInputBankNum;
    @BindView(R.id.et_input_bank_num_again)
    EditText etInputBankNumAgain;
    @BindView(R.id.et_input_bank_pwd)
    EditText etInputBankPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bank_card);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(String event) {
        Toast.makeText(this, event, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_edit_bank_card;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2.setText("编辑银行卡");

        ivTitleBarMenu2.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fl_back:
                finish();
                break;

        }
    }
}
