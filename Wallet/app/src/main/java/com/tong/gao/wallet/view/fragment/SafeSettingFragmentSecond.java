package com.tong.gao.wallet.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.event.HaveNotBindGoogleVerifyEvent;
import com.tong.gao.wallet.event.SafeSettingEventChangeCashPwd;
import com.tong.gao.wallet.event.SafeSettingEventChangeLoginPwd;
import com.tong.gao.wallet.event.SafeSettingEventFirst;
import com.tong.gao.wallet.event.SafeSettingEventGoogleVerify;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SafeSettingFragmentSecond extends Fragment implements View.OnClickListener {


    @BindView(R.id.rl_change_cash_pwd)
    RelativeLayout rlChangeCashPwd;
    @BindView(R.id.rl_change_login_pwd)
    RelativeLayout rlChangeLoginPwd;
    @BindView(R.id.rl_google_code_is_open)
    RelativeLayout rlGoogleCodeIsOpen;
    Unbinder unbinder;

    private boolean isBindGoogleCode = true;
    private boolean isOpenGoogleCode = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.from(getContext()).inflate(R.layout.fragment_safe_setting_second, container, false);

        unbinder = ButterKnife.bind(this, view);


        rlChangeCashPwd.setOnClickListener(this);
        rlChangeLoginPwd.setOnClickListener(this);
        rlGoogleCodeIsOpen.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.rl_change_cash_pwd:
                EventBus.getDefault().post(new SafeSettingEventChangeCashPwd());
                break;


            case R.id.rl_change_login_pwd:
                EventBus.getDefault().post(new SafeSettingEventChangeLoginPwd());
                break;


            case R.id.rl_google_code_is_open:

                if(!isBindGoogleCode){//没有绑定谷歌验证

                    EventBus.getDefault().post(new HaveNotBindGoogleVerifyEvent());

                }else{//绑定了谷歌验证

                    EventBus.getDefault().post(new SafeSettingEventGoogleVerify());

                    if(!isOpenGoogleCode){//没有开启谷歌验证

                    }else{//开启了谷歌验证

                    }


                }

                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
