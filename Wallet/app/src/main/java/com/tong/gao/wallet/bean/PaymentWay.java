package com.tong.gao.wallet.bean;

import com.tong.gao.wallet.constants.MyConstant;

import java.io.Serializable;

public enum PaymentWay implements Serializable {


    ZFB(MyConstant.Payment_ZFB),Wechat(MyConstant.Payment_Wechat),Bank(MyConstant.Payment_Bank);
    int way;

    PaymentWay(int way) {
        this.way = way;
    }

    public int getWay() {
        return way;
    }

}