package com.tong.gao.wallet.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.tong.gao.wallet.R;
import com.tong.gao.wallet.event.RemoveGoogleVerifyEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SafeSettingFragmentGoogleVerify extends Fragment {


    @BindView(R.id.switch_button_zfb)
    SwitchButton switchButtonZfb;
    Unbinder unbinder;
    @BindView(R.id.tv_cancel_current_bind)
    TextView tvCancelCurrentBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.fragment_google_verify, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvCancelCurrentBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RemoveGoogleVerifyEvent());
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
