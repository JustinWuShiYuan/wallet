package com.tong.gao.wallet.base.imp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.adapter.ConversationListAdapterEx;
import com.tong.gao.wallet.base.TabBasePager;
import com.tong.gao.wallet.utils.UIUtils;
import com.tong.gao.wallet.view.fragment.MyMessageFragment;
import com.tong.gao.wallet.view.fragment.MyServiceFragment;
import com.tong.gao.wallet.view.fragment.MySystemFragment;


import io.rong.imkit.RongContext;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class MessagePager extends TabBasePager implements View.OnClickListener {
    // 主界面fragment的tag
    private final String Fragment_Tag_Message = "Fragment_Tag_Message";
    private boolean isInitView = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            setTabKind(getArguments().getString("tabKindMessage"));
            initView();
        }
        return getRootView();

    }

    public static MessagePager newInstance(String tabKind) {
        MessagePager fragment = new MessagePager();
        Bundle args = new Bundle();
        args.putString("tabKindMessage", tabKind);
        fragment.setArguments(args);
        return fragment;
    }

    Fragment conversationList = null;
    private void initView() {
        if (!isInitView) {
            tvTitle.setText("消息列表");
            ibMenu.setVisibility(View.GONE);
            ibMenu.setOnClickListener(this);

            rlOrderInform.setOnClickListener(this);
            rlConnectService.setOnClickListener(this);
            rlSystemInform.setOnClickListener(this);

            if(null == conversationList){
                conversationList = initConversationList();
            }

            changeFragment(conversationList,false,R.id.iv_title_bar_menu2);

            isInitView = !isInitView;
        }

    }

    private void changeFragment(Fragment conversationList, final boolean isHideLL, int id) {

        // 获取Fragment管理器对象
        FragmentManager fm = getActivity().getSupportFragmentManager();
        // 开启事物
        FragmentTransaction ft = fm.beginTransaction(); // 得到事物操作对象
        // 替换主界面布局
        ft.replace(R.id.fl_tab_message_pager_content, conversationList, Fragment_Tag_Message);
        // 提交
        ft.commit();



        llOtherServieContainer.post(new Runnable() {
            @Override
            public void run() {
                llOtherServieContainer.setVisibility(isHideLL? View.GONE:View.VISIBLE);
                ibMenu.setVisibility(isHideLL? View.VISIBLE: View.GONE);
            }
        });


        switch (id){
            case  R.id.iv_title_bar_menu2:
                tvTitle.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText("消息列表");
                    }
                });
                break;
            case  R.id.rl_order_inform:
                tvTitle.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText("订单通知");
                    }
                });
                break;

            case R.id.rl_connect_service:
                tvTitle.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText("联系客服");

                    }
                });

                break;

            case R.id.rl_system_inform:
                tvTitle.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText("系统通知");
                    }
                });

                break;
        }

    }

    @Override
    public void initData() {

    }

    private ConversationListFragment mConversationListFragment = null;
    private boolean isDebug = false;
    private Conversation.ConversationType[] mConversationsTypes = null;
    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;
            if (isDebug) {
                uri = Uri.parse("rong://" + UIUtils.getContext().getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM,
                        Conversation.ConversationType.DISCUSSION
                };

            } else {
                uri = Uri.parse("rong://" + UIUtils.getContext().getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM
                };
            }
            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }

    }


    MyMessageFragment myMessageFragment = null; //订单通知的fragment
    MyServiceFragment myServiceFragment = null;  //联系客服
    MySystemFragment  mySystemFragment = null;  //系统通知的fragment

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.rl_order_inform:

                if(null == myMessageFragment){
                    myMessageFragment = new MyMessageFragment();
                }

                rlOrderInform.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        myMessageFragment.loadData();

                        rlOrderInform.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

                changeFragment(myMessageFragment,true,R.id.rl_order_inform);
                break;



            case R.id.rl_connect_service:
                Toast.makeText(getContext(),"点击客服",Toast.LENGTH_LONG).show();


                if(null == myServiceFragment){
                    myServiceFragment = new MyServiceFragment();
                }

                rlOrderInform.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        myServiceFragment.loadData();

                        rlOrderInform.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

                changeFragment(myServiceFragment,true,R.id.rl_connect_service);




                break;

            case R.id.rl_system_inform:

                if(null == mySystemFragment){
                    mySystemFragment = new MySystemFragment();
                }

                rlSystemInform.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mySystemFragment.loadData();

                        rlSystemInform.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

                changeFragment(mySystemFragment,true,R.id.rl_system_inform);


                break;


            case R.id.iv_title_bar_menu2:       //返回
                if(null == conversationList){
                    conversationList = initConversationList();
                }
                changeFragment(conversationList,false,R.id.iv_title_bar_menu2);
                break;


        }
    }
}
