package com.tong.gao.wallet.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.event.RemoveGoogleVerifyCodeSuccessEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SafeSettingFragmentRemoveGoogle extends Fragment implements View.OnClickListener {

    @BindView(R.id.et_input_code)
    EditText etInputCode;
    @BindView(R.id.ll_display_pwd)
    LinearLayout llDisplayPwd;
    @BindView(R.id.tv_code_1)
    TextView tvCode1;
    @BindView(R.id.tv_code_2)
    TextView tvCode2;
    @BindView(R.id.tv_code_3)
    TextView tvCode3;
    @BindView(R.id.tv_code_4)
    TextView tvCode4;
    @BindView(R.id.tv_code_5)
    TextView tvCode5;
    @BindView(R.id.tv_code_6)
    TextView tvCode6;
    @BindView(R.id.tv_remove_bind)
    TextView tvRemoveBind;

    Unbinder unbinder;

    private String googleCode ;
    private boolean inputGoogleCodeIsError = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.from(getContext()).inflate(R.layout.fragment_remove_google_verify, container, false);
        unbinder = ButterKnife.bind(this, view);
        etInputCode.addTextChangedListener(edtCodeChange);
        openKeyBoard();

        llDisplayPwd.setOnClickListener(this);
        tvRemoveBind.setOnClickListener(this);
        return view;
    }

    private void showMyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(),R.layout.normal_dialog_single,null);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void openKeyBoard() {
        etInputCode.requestFocus();
        InputMethodManager imm = (InputMethodManager) etInputCode.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 输入内容监听，投射到5个空格上
     */
    TextWatcher edtCodeChange = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            tvCode1.setText("");
            tvCode2.setText("");
            tvCode3.setText("");
            tvCode4.setText("");
            tvCode5.setText("");
            tvCode6.setText("");

            switch (s.length()) {
                case 6:
                    tvCode6.setText(s.subSequence(5, 6));
                    tvCode6.post(new Runnable() {
                        @Override
                        public void run() {
                            tvCode6.setText("*");
                        }
                    });

                case 5:
                    tvCode5.setText(s.subSequence(4, 5));
                    tvCode5.post(new Runnable() {
                        @Override
                        public void run() {
                            tvCode5.setText("*");
                        }
                    });
                case 4:
                    tvCode4.setText(s.subSequence(3, 4));
                    tvCode4.post(new Runnable() {
                        @Override
                        public void run() {
                            tvCode4.setText("*");
                        }
                    });

                case 3:
                    tvCode3.setText(s.subSequence(2, 3));
                    tvCode3.post(new Runnable() {
                        @Override
                        public void run() {
                            tvCode3.setText("*");
                        }
                    });
                case 2:
                    tvCode2.setText(s.subSequence(1, 2));
                    tvCode2.post(new Runnable() {
                        @Override
                        public void run() {
                            tvCode2.setText("*");
                        }
                    });
                case 1:
                    tvCode1.setText(s.subSequence(0, 1));
                    tvCode1.post(new Runnable() {
                        @Override
                        public void run() {
                            tvCode1.setText("*");
                        }
                    });
                default:
                    break;
            }

            //输入完5个验证码 自动请求验证
            if (s.length() == 6) {
                googleCode = s.toString();
//                clickNext();
            }

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ll_display_pwd:
                openKeyBoard();
                break;


            case R.id.tv_remove_bind:
                //TODO  请求借口 看验证码是否错误
                if(inputGoogleCodeIsError){
                    showMyDialog();
                }else{
                    EventBus.getDefault().post(new RemoveGoogleVerifyCodeSuccessEvent());
                }
                break;
        }
    }
}
