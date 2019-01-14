package com.tong.gao.wallet.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.suke.widget.SwitchButton;
import com.tong.gao.wallet.R;
import com.tong.gao.wallet.base.trade.SingleTradeFragment;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.utils.StringUtils;
import com.tong.gao.wallet.view.NoScrollViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SaleCoinActivity extends BaseActivity implements  View.OnClickListener {
    private TextView    tvTitle;
    private FrameLayout ivBack;

    private TextView    tvLimitTitle;
    private View        lineLimitTitle;
    private TextView    tvConstantTitle;
    private View        lineConstantTitle;
//    private TabLayout   tabTrade;
    private NoScrollViewPager vpTrade;
    private String[]    tabTitles ={"单笔交易限额","单笔交易固额"};
    private List<Fragment> tradeFragments = new ArrayList<>();


    private SwitchButton    sbZFB;
    private SwitchButton    sbWechat;
    private SwitchButton    sbBank;
    private EditText        etFastReply;
    private CheckBox        cbHeightAuthentication;
    private CheckBox        cbNoTradeOtherPlatform;
    private CheckBox        cbAgreeProtocol;
    private TextView        tvTradeProtocol;
    private Button          btnPublishAdvertisement;

    private RelativeLayout  rlSafeRootView;
    private LinearLayout    llSafeRootView;
    private TextView        tvCancelVerify;
    private EditText        etPaymentPwd;
    private EditText        etGooglePwd;
    private Button          btnSureSafeCode;

    private String          coinTradeType;

    @Override
    protected int getLayout() {
        return R.layout.activity_sale_coin;
    }


    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tv_title_bar_title2);
        tvTitle.setText("发布广告");
        ivBack = findViewById(R.id.fl_back);
        ivBack.setOnClickListener(this);

//        setTabTitles(tabTitles);
//        tabTrade = findViewById(R.id.tab_layout_trade);
//        tabTrade.setSelectedTabIndicatorColor(Color.parseColor("#587BFC"));//设置选中时的指示器的颜色
//        tabTrade.setTabMode(TabLayout.MODE_SCROLLABLE);//可滑动，默认是FIXED
        tvLimitTitle = findViewById(R.id.tv_trade_limit_title);
        tvLimitTitle.setOnClickListener(this);
        lineLimitTitle = findViewById(R.id.line_trade_limit_title);

        tvConstantTitle = findViewById(R.id.tv_trade_constant_title);
        tvConstantTitle.setOnClickListener(this);
        lineConstantTitle = findViewById(R.id.line_trade_constant_title);


        vpTrade = findViewById(R.id.view_pager_trade);
        loadData();

        sbZFB = findViewById(R.id.switch_button_zfb);
        sbZFB.setOnClickListener(this);
        sbZFB.setChecked(true);
        sbWechat = findViewById(R.id.switch_button_we_chat);
        sbZFB.setOnClickListener(this);
        sbBank = findViewById(R.id.switch_button_bank);
        sbZFB.setOnClickListener(this);

        etFastReply = findViewById(R.id.et_fast_reply);

        cbHeightAuthentication = findViewById(R.id.cb_height_authentication);
        cbHeightAuthentication.setChecked(true);
        cbHeightAuthentication.setOnClickListener(this);

        cbNoTradeOtherPlatform = findViewById(R.id.cb_no_trade_other_platform);
        cbNoTradeOtherPlatform.setChecked(true);
        cbNoTradeOtherPlatform.setOnClickListener(this);

        cbAgreeProtocol = findViewById(R.id.cb_agree_protocol);
        cbAgreeProtocol.setOnClickListener(this);

        tvTradeProtocol = findViewById(R.id.tv_trade_protocol);
        tvTradeProtocol.setOnClickListener(this);

        btnPublishAdvertisement = findViewById(R.id.btn_publish_advertisement);
        btnPublishAdvertisement.setOnClickListener(this);
        btnPublishAdvertisement.setClickable(false);

        rlSafeRootView = findViewById(R.id.rl_safe_root_view);
        llSafeRootView = findViewById(R.id.ll_safe_root_view);
        tvCancelVerify = findViewById(R.id.tv_cancel_verify);
        tvCancelVerify.setOnClickListener(this);

        etPaymentPwd = findViewById(R.id.et_payment_pwd);
        etGooglePwd = findViewById(R.id.et_google_pwd);
        btnSureSafeCode = findViewById(R.id.btn_sure_safe_code);
        btnSureSafeCode.setOnClickListener(this);

        if(null != myIntent){
            coinTradeType = myIntent.getStringExtra(MyConstant.CoinLimit);
            if(!StringUtils.isEmpty(coinTradeType) && MyConstant.CoinLimit.equals(coinTradeType)){
                tvConstantTitle.setTextColor(Color.parseColor("#9B9B9B"));
                tvConstantTitle.setClickable(false);
                cbAgreeProtocol.setChecked(true);
            }else if(!StringUtils.isEmpty(coinTradeType) && MyConstant.CoinConstant.equals(coinTradeType)){
                tvLimitTitle.setTextColor(Color.parseColor("#9B9B9B"));
                tvLimitTitle.setClickable(false);
                cbAgreeProtocol.setChecked(true);
            }
        }
    }

    private void loadData() {
        for (int i = 0; i < tabTitles.length; i++) {
            tradeFragments.add(SingleTradeFragment.newInstance(i+1));
        }
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), tradeFragments);
        //给ViewPager设置适配器
        vpTrade.setAdapter(adapter);

    }

    @Override
    public void setOrientation() {
        Density.setOrientation(this,AppUtils.HEIGHT);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello ( String event){
        Toast.makeText( this , event , Toast.LENGTH_SHORT).show();
    }



    private void changePublishBg() {
        GradientDrawable drawable =(GradientDrawable)btnPublishAdvertisement.getBackground();
        if(cbAgreeProtocol.isChecked()){
            btnPublishAdvertisement.setClickable(true);
            drawable.setColor(getResources().getColor(R.color.colorBlue2));
        }else{
            btnPublishAdvertisement.setClickable(false);
            drawable.setColor(getResources().getColor(R.color.gray_2));
        }
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.fl_back:         //
                this.finish();
                break;

            case R.id.tv_trade_limit_title:         //
                if(lineLimitTitle.getVisibility() == View.INVISIBLE){
                    tvLimitTitle.setTextColor(Color.parseColor("#587BFC"));
                    lineLimitTitle.setVisibility(View.VISIBLE);

                    tvConstantTitle.setTextColor(Color.parseColor("#666666"));
                    lineConstantTitle.setVisibility(View.INVISIBLE);

                    vpTrade.setCurrentItem(0);
                }

                break;

            case R.id.tv_trade_constant_title:         //
                if(lineConstantTitle.getVisibility() == View.INVISIBLE){
                    tvConstantTitle.setTextColor(Color.parseColor("#587BFC"));
                    lineConstantTitle.setVisibility(View.VISIBLE);

                    tvLimitTitle.setTextColor(Color.parseColor("#666666"));
                    lineLimitTitle.setVisibility(View.INVISIBLE);

                    vpTrade.setCurrentItem(1);
                }

                break;

            case R.id.tv_trade_protocol:         //TODO跳转 h5 协议页面

                break;

            case R.id.btn_publish_advertisement:   //发布广告
                rlSafeRootView.setVisibility(View.VISIBLE);
                int flSafeRootViewHeight = llSafeRootView.getMeasuredHeight();
                int height = llSafeRootView.getHeight();
                LogUtils.d("flSafeRootViewHeight:"+flSafeRootViewHeight+" height:"+height);
                ObjectAnimator translation = ObjectAnimator.ofFloat(llSafeRootView, "translationY", 532, 0);
                translation.setDuration(800);
                translation.start();
                llSafeRootView.setVisibility(View.VISIBLE);


                break;

            case R.id.tv_cancel_verify:         //取消安全验证

                int height1 = llSafeRootView.getHeight();
                ObjectAnimator translation1 = ObjectAnimator.ofFloat(llSafeRootView, "translationY", 0, height1);
                translation1.setDuration(500);
                translation1.start();
                translation1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        rlSafeRootView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
//
                break;


            case R.id.btn_sure_safe_code:       //提交安全验证

//                etPaymentPwd = findViewById(R.id.et_payment_pwd);
//                 = findViewById(R.id.et_google_pwd);
                if(!StringUtils.isEmpty(etPaymentPwd.getText().toString())&&
                        !StringUtils.isEmpty(etGooglePwd.getText().toString())){

                    startActivity(new Intent(SaleCoinActivity.this,PublishAdvertisementActivity.class));
                    this.finish();
                    
                }else{
                    Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                
                break;

            case R.id.cb_height_authentication:

//                changePublishBg();
                break;

            case R.id.cb_no_trade_other_platform:
//                changePublishBg();
                break;

            case R.id.cb_agree_protocol:
                changePublishBg();
                break;

            case R.id.switch_button_zfb:

                break;

            case R.id.switch_button_we_chat:

                break;

            case R.id.switch_button_bank:

                break;

//
        }
    }
}
