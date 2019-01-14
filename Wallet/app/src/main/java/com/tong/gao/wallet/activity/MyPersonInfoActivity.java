package com.tong.gao.wallet.activity;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tong.gao.wallet.R;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyPersonInfoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout ivTitleBarMenu2;
    @BindView(R.id.civ_user_head_src)
    CircleImageView civUserHeadSrc;
    @BindView(R.id.tv_my_id)
    TextView tvMyId;
    @BindView(R.id.tv_my_nick_name)
    EditText tvMyNickName;
    @BindView(R.id.tv_my_user_name)
    TextView tvMyUserName;

    @BindView(R.id.tv_exit_login)
    TextView tvExitLogin;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello ( String event){
        Toast.makeText( this , event , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_person_info;
    }


    @Override
    protected void initView() {
        tvTitleBarTitle2.setText("个人信息");
        ivTitleBarMenu2.setOnClickListener(this);


        //TODO 加载数据

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.head_loading)
                .error(R.drawable.head_loading)
                .fallback(new ColorDrawable(Color.RED));

        Glide.with(this)
                .load("img")
                .apply(requestOptions)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Drawable current = resource.getCurrent();
                        civUserHeadSrc.setImageDrawable(current);
                    }
                });



//        tvMyId  tvMyNickName  tvMyUserName

        tvMyNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        tvExitLogin.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_person_info);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fl_back:
                 this.finish();

                break;

            case R.id.tv_exit_login:
                Toast.makeText(MyPersonInfoActivity.this,"sss",Toast.LENGTH_SHORT).show();

                break;


        }

    }
}
