package com.tong.gao.wallet.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tong.gao.wallet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SafeSettingFragmentChangLoginPwd extends Fragment {


    @BindView(R.id.et_input_new_login_pwd)
    EditText etInputNewLoginPwd;
    @BindView(R.id.et_input_new_login_pwd_again)
    EditText etInputNewLoginPwdAgain;
    @BindView(R.id.et_input_google_pwd)
    EditText etInputGooglePwd;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.fragment_change_login_pwd, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
