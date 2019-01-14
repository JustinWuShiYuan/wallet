package com.tong.gao.wallet.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.event.SafeSettingEventFirst;
import com.tong.gao.wallet.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SafeSettingFragmentFrist extends Fragment implements View.OnClickListener {

    @BindView(R.id.et_fund_pwd)
    EditText etFundPwd;
    @BindView(R.id.et_sure_pwd)
    EditText etSurePwd;
    @BindView(R.id.tv_sure_setting)
    TextView tvSureSetting;
    Unbinder unbinder;

    private String fundPwd,surePwd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.from(getContext()).inflate(R.layout.fragment_safe_setting_first, container, false);
        unbinder = ButterKnife.bind(this, view);

        tvSureSetting.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        fundPwd = etFundPwd.getText().toString();
        surePwd = etSurePwd.getText().toString();
        if(!StringUtils.isEmpty(fundPwd) && !StringUtils.isEmpty(surePwd)){
            //TODO 调用接口验证  如果通过 抛一个event事件
            EventBus.getDefault().post(new SafeSettingEventFirst());

        }else{
            Toast.makeText(getActivity(),"密码不能为空",Toast.LENGTH_LONG).show();
        }
    }
}
