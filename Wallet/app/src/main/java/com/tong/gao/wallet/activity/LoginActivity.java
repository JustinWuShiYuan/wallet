package com.tong.gao.wallet.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.bean.LoginResponseInfo;
import com.tong.gao.wallet.bean.RequestLoginInfoBean;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.factory.ThreadPoolFactory;
import com.tong.gao.wallet.net.NetWorks;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_input_account)
    EditText etInputAccount;
    @BindView(R.id.et_input_account_pwd)
    EditText etInputAccountPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.rl_logining_root_view)
    RelativeLayout rlLoginingRootView;

    private String loginName;
    private String loginPwd;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(String event) {
        Toast.makeText(this, event, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginName = etInputAccount.getText().toString();
                loginPwd = etInputAccountPwd.getText().toString();

                if (!StringUtils.isEmpty(loginName) && !StringUtils.isEmpty(loginPwd)) {
                    toLogin(loginName, loginPwd);
                } else {
                    Toast.makeText(LoginActivity.this,"账号密码不能为空",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void toLogin(final String loginName, final String loginPwd) {
        loginDialog(true);


        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();


        loginDialog(false);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);



        ThreadPoolFactory.getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                NetWorks.login(new RequestLoginInfoBean(loginName, loginPwd), new Observer<LoginResponseInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LoginResponseInfo loginResponseInfo) {



                        if (null != loginResponseInfo.getUserinfo() && loginResponseInfo.getUserinfo().getSafeverifyswitch()
                                .equals(MyConstant.googleVerifyIsOpened)) {//开了谷歌验证

                            Intent intent = new Intent(LoginActivity.this, SecondVerifyActivity.class);
                            startActivity(intent);
                            finish();



                        } else {

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        loginDialog(false);
                        overridePendingTransition(android.R.anim.fade_in,
                                android.R.anim.fade_out);


                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("e:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("订阅完成....");
                    }
                });
            }
        });
    }

    AlertDialog.Builder builder;
    AlertDialog dialogLogin;

    private void loginDialog(boolean isDisplay) {
        if (isDisplay) {
//            if(null == builder){
//                builder = new AlertDialog.Builder(LoginActivity.this);
//                View view = View.inflate(LoginActivity.this,R.layout.logining,null);
//                builder.setView(view);
//                builder.setCancelable(false);
//            }
//            dialogLogin = builder.create();
//            dialogLogin.show();
            rlLoginingRootView.setVisibility(View.VISIBLE);
        } else {
            rlLoginingRootView.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }
}
