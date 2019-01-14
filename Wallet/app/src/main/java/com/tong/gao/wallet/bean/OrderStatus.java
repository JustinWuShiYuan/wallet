package com.tong.gao.wallet.bean;

import com.tong.gao.wallet.constants.MyConstant;

import java.io.Serializable;

public enum OrderStatus implements Serializable {


    ALL(MyConstant.Status_All),
    Complete(MyConstant.Status_Complete), Cancel_TimeOut(MyConstant.Status_Cancel_TimeOut),Cancel_Buyer(MyConstant.Status_Cancel_Buyer),
    WillGreenLight(MyConstant.Status_WillGreenLight),NotPay(MyConstant.Status_NotPay)
    , Appealing(MyConstant.Status_Appeal);

    int	state;

    OrderStatus(int state) {
        this.state = state;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }



}