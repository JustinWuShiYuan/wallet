package com.tong.gao.wallet.view.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.event.ChangeTitleGoogleVerifyEvent;
import com.tong.gao.wallet.event.HaveNotBindGoogleVerifyEvent;
import com.tong.gao.wallet.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InstallGoogleVerifyFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.ll_first_step)
    LinearLayout llFirstStep;
    Unbinder unbinder;
    @BindView(R.id.tv_next_step)
    TextView tvNextStep;

    @BindView(R.id.et_input_google_verify_code)
    EditText etInputGoogleVerifyCode;
    @BindView(R.id.rl_copy_google_code)
    RelativeLayout rlCopyGoogleCode;
    @BindView(R.id.ll_second_step)
    LinearLayout llSecondStep;

    @BindView(R.id.et_input_google_verify_code_again)
    EditText etInputGoogleVerifyCodeAgain;
    @BindView(R.id.ll_third_step)
    LinearLayout llThirdStep;

    @BindView(R.id.ll_fourth_step)
    LinearLayout llFourthStep;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.et_google_pwd)
    EditText etGooglePwd;
    @BindView(R.id.ll_complete_google_verify)
    LinearLayout llCompleteGoogleVerify;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.ll_verify_success)
    LinearLayout llVerifySuccess;

    private int currentStepIndex = 1;
    private final int currentStep1 = 1;
    private final int currentStep2 = 2;
    private final int currentStep3 = 3;
    private final int currentStep4 = 4;
    private final int currentStep5 = 5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.install_google_verify_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);



        tvNextStep.setOnClickListener(this);
        rlCopyGoogleCode.setOnClickListener(this);
        llThirdStep.setOnClickListener(this);
        llFourthStep.setOnClickListener(this);
        tvBack.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onResume() {
        super.onResume();
        currentStepIndex = 1;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_next_step:
                changeStep(currentStepIndex);


                break;


            case R.id.rl_copy_google_code:

                copyCode();

                break;

            case R.id.tv_back:  //回到首页

                 getActivity().finish();

                break;

        }
    }

    private void changeStep(int stepIndex) {

        LogUtils.d("changeStep stepIndex:"+stepIndex);

        switch (stepIndex) {

            case currentStep1:
                llFirstStep.setVisibility(View.GONE);
                llSecondStep.setVisibility(View.VISIBLE);
                break;


            case currentStep2:
                llSecondStep.setVisibility(View.GONE);
                llThirdStep.setVisibility(View.VISIBLE);
                break;


            case currentStep3:
                llThirdStep.setVisibility(View.GONE);
                llFourthStep.setVisibility(View.VISIBLE);
                EventBus.getDefault().post(new ChangeTitleGoogleVerifyEvent(8));
                break;


            case currentStep4:
                llFourthStep.setVisibility(View.GONE);
                llCompleteGoogleVerify.setVisibility(View.VISIBLE);
                EventBus.getDefault().post(new ChangeTitleGoogleVerifyEvent(9));
                break;


            case currentStep5:
                llCompleteGoogleVerify.setVisibility(View.GONE);
                tvNextStep.setVisibility(View.GONE);
                llVerifySuccess.setVisibility(View.VISIBLE);
                EventBus.getDefault().post(new ChangeTitleGoogleVerifyEvent(10));
                break;

        }

        currentStepIndex++;

    }





    private void copyCode() {

        //添加到剪切板
        ClipboardManager clipboardManager =
                (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        assert clipboardManager != null;
        clipboardManager.setPrimaryClip(ClipData.newPlainText(null, etInputGoogleVerifyCode.getText()));
        if (clipboardManager.hasPrimaryClip()) {
            clipboardManager.getPrimaryClip().getItemAt(0).getText();
            Toast.makeText(getActivity(), "ddd", Toast.LENGTH_LONG).show();
        }
    }
}
