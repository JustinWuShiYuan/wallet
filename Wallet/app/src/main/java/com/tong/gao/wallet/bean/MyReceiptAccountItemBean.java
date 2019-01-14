package com.tong.gao.wallet.bean;

import java.io.Serializable;

public class MyReceiptAccountItemBean implements Serializable {
    private PaymentWay getMoneyType;
    private boolean     isOpen;
    private String      accountNum;


    public MyReceiptAccountItemBean(PaymentWay getMoneyType, boolean isOpen, String accountNum) {
        this.getMoneyType = getMoneyType;
        this.isOpen = isOpen;
        this.accountNum = accountNum;
    }

    public PaymentWay getGetMoneyType() {
        return getMoneyType;
    }

    public void setGetMoneyType(PaymentWay getMoneyType) {
        this.getMoneyType = getMoneyType;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    @Override
    public String toString() {
        return "MyReceiptAccountItemBean{" +
                "getMoneyType=" + getMoneyType +
                ", isOpen=" + isOpen +
                ", accountNum='" + accountNum + '\'' +
                '}';
    }
}
