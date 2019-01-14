package com.tong.gao.wallet.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.adapter.MyAccountAdapter;
import com.tong.gao.wallet.bean.MyReceiptAccountItemBean;
import com.tong.gao.wallet.bean.PaymentWay;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.AppUtils;
import com.tong.gao.wallet.utils.Density;
import com.tong.gao.wallet.utils.LogUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyReceiptAccountActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;

    @BindView(R.id.fl_add)
    FrameLayout flTitleAdd;

    @BindView(R.id.rv_my_receipt)
    SwipeMenuRecyclerView rvMyReceipt;

    @BindView(R.id.btn_sure_safe_code)
    Button btnSureSafeCode;
    @BindView(R.id.rl_cancel)
    RelativeLayout rlCancelAddAccount;
    @BindView(R.id.rl_add_account_root_view)
    RelativeLayout rlAddAccountRootView;
    @BindView(R.id.ll_add_account_root_view)
    LinearLayout llAddAccountRootView;


    @BindView(R.id.ib_zfb_switch)
    ImageButton ibZfbSwitch;
    @BindView(R.id.ib_we_chat_switch)
    ImageButton ibWeChatSwitch;
    @BindView(R.id.ib_bank_switch)
    ImageButton ibBankSwitch;

    private List<MyReceiptAccountItemBean> accountItemBeanList = new ArrayList<>();
    private MyAccountAdapter myAccountAdapter = null;


    public static final String TOP_DECORATION = "top_decoration";
    public static final String BOTTOM_DECORATION = "bottom_decoration";
    public static final String LEFT_DECORATION = "left_decoration";
    public static final String RIGHT_DECORATION = "right_decoration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_receipt_account);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_receipt_account;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2.setText("收款账户");

        flBack.setOnClickListener(this);
        flTitleAdd.setOnClickListener(this);
        rlCancelAddAccount.setOnClickListener(this);
        ibZfbSwitch.setOnClickListener(this);
        ibWeChatSwitch.setOnClickListener(this);
        ibBankSwitch.setOnClickListener(this);
        btnSureSafeCode.setOnClickListener(this);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        rvMyReceipt.setLayoutManager(mLinearLayoutManager);

        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(TOP_DECORATION, 20);//top间距
        stringIntegerHashMap.put(LEFT_DECORATION, 10);//top间距
        stringIntegerHashMap.put(RIGHT_DECORATION, 10);//top间距

        rvMyReceipt.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        rvMyReceipt.setSwipeMenuCreator(swipeMenuCreator);
        rvMyReceipt.setSwipeMenuItemClickListener(mMenuItemClickListener);

        for (int i = 0; i < 5; i++) {
            accountItemBeanList.add(new MyReceiptAccountItemBean(PaymentWay.ZFB, true, "2321321:" + i));
        }

        if (null == myAccountAdapter) {
            myAccountAdapter = new MyAccountAdapter(this);
        }
        rvMyReceipt.setAdapter(myAccountAdapter);

        myAccountAdapter.notifyDataSetChanged(accountItemBeanList);

    }

    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(String event) {
        Toast.makeText(this, event, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fl_back:
                this.finish();

                break;


            case R.id.fl_add:

                rlAddAccountRootView.setVisibility(View.VISIBLE);
                int flSafeRootViewHeight = llAddAccountRootView.getMeasuredHeight();
                int height = llAddAccountRootView.getHeight();
                LogUtils.d("flSafeRootViewHeight:" + flSafeRootViewHeight + " height:" + height);
                ObjectAnimator translation = ObjectAnimator.ofFloat(llAddAccountRootView, "translationY", 532, 0);
                translation.setDuration(800);
                translation.start();
                llAddAccountRootView.setVisibility(View.VISIBLE);

                break;


            case R.id.rl_cancel:

                hideAddAccountRootView();

                break;


            case R.id.ib_zfb_switch:

                changeSwitchBg(R.id.ib_zfb_switch);

                break;


            case R.id.ib_we_chat_switch:
                changeSwitchBg(R.id.ib_we_chat_switch);

                break;


            case R.id.ib_bank_switch:
                changeSwitchBg(R.id.ib_bank_switch);

                break;


            case R.id.btn_sure_safe_code:
                 hideAddAccountRootView();
                 startActivityById(paymentTypeId);

                break;


        }
    }

    private void hideAddAccountRootView() {
        int height1 = rlAddAccountRootView.getHeight();
        ObjectAnimator translation1 = ObjectAnimator.ofFloat(llAddAccountRootView, "translationY", 0, height1);
        translation1.setDuration(500);
        translation1.start();
        translation1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rlAddAccountRootView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    private void startActivityById(int paymentTypeId) {
        switch (paymentTypeId){

            case R.id.ib_zfb_switch:
                Intent intent = new Intent();
                intent.putExtra(MyConstant.EditQRcodeType,"ZFB");
                intent.setClass(MyReceiptAccountActivity.this,EditZFBOrWechatCodeActivity.class);
                startActivity(intent);
                break;


            case R.id.ib_we_chat_switch:

                Intent intent1 = new Intent();
                intent1.putExtra(MyConstant.EditQRcodeType,"WeChat");
                intent1.setClass(MyReceiptAccountActivity.this,EditZFBOrWechatCodeActivity.class);
                startActivity(intent1);

                break;


            case R.id.ib_bank_switch:
                startActivity(new Intent(MyReceiptAccountActivity.this,EditBankCardActivity.class));

                break;
        }

    }

    private int paymentTypeId;
    private void changeSwitchBg(int id) {
        paymentTypeId = id;
        switch (id){

            case R.id.ib_zfb_switch:
                ibZfbSwitch.setBackgroundResource(R.drawable.trick_selected);
                ibWeChatSwitch.setBackgroundResource(R.drawable.trick_unselect);
                ibBankSwitch.setBackgroundResource(R.drawable.trick_unselect);

                break;


            case R.id.ib_we_chat_switch:
                ibZfbSwitch.setBackgroundResource(R.drawable.trick_unselect);
                ibWeChatSwitch.setBackgroundResource(R.drawable.trick_selected);
                ibBankSwitch.setBackgroundResource(R.drawable.trick_unselect);

                break;


            case R.id.ib_bank_switch:
                ibZfbSwitch.setBackgroundResource(R.drawable.trick_unselect);
                ibWeChatSwitch.setBackgroundResource(R.drawable.trick_unselect);
                ibBankSwitch.setBackgroundResource(R.drawable.trick_selected);

                break;
        }

    }


    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_120);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(MyReceiptAccountActivity.this).setBackground(R.color.colorWhite)
//                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除")
                        .setTextColor(Color.RED)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
            }
        }
    };


    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                showMyDialog(position);

            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(MyReceiptAccountActivity.this, "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };

    private void showMyDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyReceiptAccountActivity.this);
        View view = View.inflate(MyReceiptAccountActivity.this,R.layout.normal_dialog,null);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                accountItemBeanList.remove(position);
                myAccountAdapter.notifyDataSetChanged(accountItemBeanList);
            }
        });

    }




    class RecyclerViewSpacesItemDecoration extends RecyclerView.ItemDecoration {

        HashMap<String, Integer> mSpaceValueMap;

        public RecyclerViewSpacesItemDecoration(HashMap<String, Integer> mSpaceValueMap) {
            this.mSpaceValueMap = mSpaceValueMap;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            if (mSpaceValueMap.get(TOP_DECORATION) != null)
                outRect.top = mSpaceValueMap.get(TOP_DECORATION);
            if (mSpaceValueMap.get(LEFT_DECORATION) != null)

                outRect.left = mSpaceValueMap.get(LEFT_DECORATION);
            if (mSpaceValueMap.get(RIGHT_DECORATION) != null)
                outRect.right = mSpaceValueMap.get(RIGHT_DECORATION);
            if (mSpaceValueMap.get(BOTTOM_DECORATION) != null)
                outRect.bottom = mSpaceValueMap.get(BOTTOM_DECORATION);

        }


    }
}
