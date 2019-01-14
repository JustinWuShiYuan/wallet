package com.tong.gao.wallet.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.event.ChangeTitleGoogleVerifyEvent;
import com.tong.gao.wallet.event.HaveNotBindGoogleVerifyEvent;
import com.tong.gao.wallet.event.RemoveGoogleVerifyCodeSuccessEvent;
import com.tong.gao.wallet.event.RemoveGoogleVerifyEvent;
import com.tong.gao.wallet.event.SafeSettingEventChangeCashPwd;
import com.tong.gao.wallet.event.SafeSettingEventChangeLoginPwd;
import com.tong.gao.wallet.event.SafeSettingEventFirst;
import com.tong.gao.wallet.event.SafeSettingEventGoogleVerify;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.view.fragment.InstallGoogleVerifyFragment;
import com.tong.gao.wallet.view.fragment.SafeSettingFragmentChangLoginPwd;
import com.tong.gao.wallet.view.fragment.SafeSettingFragmentChangeCashPwd;
import com.tong.gao.wallet.view.fragment.SafeSettingFragmentFrist;
import com.tong.gao.wallet.view.fragment.SafeSettingFragmentGoogleVerify;
import com.tong.gao.wallet.view.fragment.SafeSettingFragmentRemoveGoogle;
import com.tong.gao.wallet.view.fragment.SafeSettingFragmentSecond;
import com.tong.gao.wallet.view.fragment.UnbindGoogleSuccessFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SafeSettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.fl_safe_setting_pager_content)
    FrameLayout flTabMessagePagerContent;

    // 主界面fragment的tag
    private final String Fragment_Tag_Safe_Setting = "Fragment_Tag_Safe_Setting";

    private Fragment    fragmentFirst;
    private Fragment    fragmentSecond;
    private Fragment    fragmentThird;
    private Fragment    fragmentFourth;
    private Fragment    fragmentFifth;
    private Fragment    fragmentSixth;
    private Fragment    fragmentRemoveSuccess;
    private Fragment    fragmentHasNotBindGoogle;
    private int         fragmentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_setting);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_safe_setting;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2.setText("安全设置");
        flBack.setOnClickListener(this);
        //构建默认的第一个fragment
        if(null == fragmentFirst){
            fragmentIndex = 0;
            fragmentFirst = new SafeSettingFragmentFrist();
        }

        changeFragment(fragmentFirst,0);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventFirst(SafeSettingEventFirst event) {
        if(null == fragmentSecond){
            fragmentSecond = new SafeSettingFragmentSecond();
        }
        changeFragment(fragmentSecond,1);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventSecond(SafeSettingEventChangeCashPwd event) {
        if(null == fragmentThird){
            fragmentThird = new SafeSettingFragmentChangeCashPwd();
        }
        changeFragment(fragmentThird,2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventChangeLoginPwd(SafeSettingEventChangeLoginPwd event) {
        if(null == fragmentFourth){
            fragmentFourth = new SafeSettingFragmentChangLoginPwd();
        }
        changeFragment(fragmentFourth,3);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventGoogleVerify(SafeSettingEventGoogleVerify event) {
        if(null == fragmentFifth){
            fragmentFifth = new SafeSettingFragmentGoogleVerify();
        }
        changeFragment(fragmentFifth,4);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventRemoveGoogleVerify(RemoveGoogleVerifyEvent event) {
        if(null == fragmentSixth){
            fragmentSixth = new SafeSettingFragmentRemoveGoogle();
        }
        changeFragment(fragmentSixth,5);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventRemoveGoogleSuccess(RemoveGoogleVerifyCodeSuccessEvent event) {
        if(null == fragmentRemoveSuccess){
            fragmentRemoveSuccess = new UnbindGoogleSuccessFragment();
        }
        changeFragment(fragmentRemoveSuccess,6);
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventHaveNotBindGoogle(HaveNotBindGoogleVerifyEvent event) {
        LogUtils.d("------------------------333333333333333333333333333");
        if(null == fragmentHasNotBindGoogle){
            fragmentHasNotBindGoogle = new InstallGoogleVerifyFragment();
        }
        changeFragment(fragmentHasNotBindGoogle,7);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventChangeTitleGoogle(ChangeTitleGoogleVerifyEvent event) {
        if(null == fragmentHasNotBindGoogle){
            fragmentHasNotBindGoogle = new InstallGoogleVerifyFragment();
        }
        changeFragment(fragmentHasNotBindGoogle,event.getIndexStep());
    }


    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }


    private void changeFragment(Fragment conversationList, int id) {
        fragmentIndex = id;
        // 获取Fragment管理器对象
        FragmentManager fm = getSupportFragmentManager();
        // 开启事物
        FragmentTransaction ft = fm.beginTransaction(); // 得到事物操作对象
        // 替换主界面布局
        ft.replace(R.id.fl_safe_setting_pager_content, conversationList, Fragment_Tag_Safe_Setting);
        // 提交
        ft.commit();



        LogUtils.d("changeFragment fragmentIndex:"+fragmentIndex);

        String titleName = "";
        if(fragmentIndex == 0 || fragmentIndex ==1){
            titleName = "安全设置";
        }else if(fragmentIndex == 2){
            titleName = "资金密码";
        }else if(fragmentIndex == 3){
            titleName = "登录密码";
        }else if(fragmentIndex == 4){
            titleName = "谷歌验证";
        }else if(fragmentIndex == 5 || fragmentIndex == 6){
            titleName = "解除谷歌验证";
        }else if(fragmentIndex == 7){
            titleName = "下载并安装";
        }else if(fragmentIndex == 8){
            titleName = "设置验证码";
        }else if(fragmentIndex == 9){
            titleName = "完成绑定";
        }else if(fragmentIndex == 10){
            titleName = "谷歌验证";
        }

        final String finalTitleName = titleName;
        tvTitleBarTitle2.post(new Runnable() {
            @Override
            public void run() {
                tvTitleBarTitle2.setText(finalTitleName);
            }
        });

    }

    @Override
    public void onClick(View v) {
        LogUtils.d("fragmentIndex:"+fragmentIndex);
        switch (v.getId()){

            case R.id.fl_back:

                if(fragmentIndex == 0 || fragmentIndex == 1){
                    this.finish();
                }else if(fragmentIndex == 2 || fragmentIndex == 3 || fragmentIndex == 4){
                    changeFragment(fragmentSecond,1);
                }else if(fragmentIndex == 5 || fragmentIndex == 6){
                    changeFragment(fragmentFifth,4);
                }else if(fragmentIndex >= 7){
                    changeFragment(fragmentSecond,1);
//                    this.finish();
                }

                break;

        }
    }
}
