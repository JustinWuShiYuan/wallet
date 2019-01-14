package com.tong.gao.wallet.constants;

public class MyConstant {
    public static final String mySecretKeyGoogle = "THKRP5JCTPIU5CEE";  //谷歌验证 关闭

    public static String baseUrl = "http://47.52.45.85:8010/";//测试地址
    public static final String login = "ug/usr/pblin.do";           //登录
    public static final String verify_google_code = "ug/usr/pbggc.do";           //校验谷歌验证码

    public static final String googleVerifyIsClosed = "0";  //谷歌验证 关闭

    public static final String googleVerifyIsOpened = "1";  //谷歌验证 打开



    //-----------------------------------------------------
    public static String token="K5nLqSUVNzmq4SR4/GniW9uhn6eH2xtUN/0uspaHfZQATePz6cbap1iaRy5s5OVAvDyWYExVbcsUuY3m7TEfqA==";
//    public static String token="VjyD12Jpq8JXjUB0lp2Fr6jzxfYvq48n+4ELzl8nCxitIvpBQFJWPQhZo/mn0PAyuA06macF/ZEYv2fgY8zu2T0uquKvKN6l";
    public  static final String Tab_1="Tab_1";   //     第一类tab对应首页
    public  static final String Tab_2="Tab_2";   //     第二类tab对应其他页面
    public  static final String Tab_3="Tab_3";   //     第二类tab对应其他页面

    public  static final String Middle_Flcontent_HomePager ="1";   //
    public  static final String Middle_Flcontent_Trade ="2";   //
    public  static final String Middle_Flcontent_Message ="3";   //
    public  static final String Middle_Flcontent_MyInfo ="4";   //

    public  static final String CoinLimit ="CoinLimit";   //
    public  static final String CoinConstant ="CoinConstant";   //

    public static final int singleLimitTrade = 1;
    public static final int singleConstantTrade = 2;
    public static final String MyOrderType = "MyOrderType";
    public static final String MyTradeType = "MyTradeType";
    public static final String MyOrderTypeStatus = "MyOrderTypeStatus";

    public static final int Status_All =0;
    public static final int Status_Complete =5;
    public static final int Status_Cancel_TimeOut =4;
    public static final int Status_Cancel_Buyer =7;
    public static final int Status_WillGreenLight =2;//待放行
    public static final int Status_NotPay =1;      //等待付款
    public static final int Status_Appeal =3;      //申诉中
    //兑换币 订单的状态
    public static final String Exchange_Order_Status ="Exchange_Order_Status";      //兑换币 订单的状态

    //我的广告、交易 状态
    public static final int Status_Trade_All =10;
    public static final int Status_Trade_OnOffering =11;//出售中
    public static final int Status_Trade_SellOut =12;//售罄
    public static final int Status_Trade_SoldOut =13;//下架

    public static final int Status_Trade_ConstantDenomination =14;//下架
    public static final int Status_Trade_LimitDenomination =15;//下架


    public static final int Payment_ZFB =16;
    public static final int Payment_Wechat =17;
    public static final int Payment_Bank =18;

    public static final String Empty_ViewType ="Empty_ViewType";
    public static final String Empty_ViewTypeOf_Trade ="Empty_ViewTypeOf_Trade";

    public static final String EditQRcodeType = "EditQRcodeType";
}
