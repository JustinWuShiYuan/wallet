package com.tong.gao.wallet.bean;

import com.tong.gao.wallet.base.imp.HomePager;

import java.io.Serializable;
import java.util.List;

public class PendingOrderBean implements Serializable {
    private String orderTime;
    private long countDownTime;
    private String orderUserName;
    private String userBuyCoinNum;

    public PendingOrderBean(String orderTime, long countDownTime, String orderUserName, String userBuyCoinNum) {
        this.orderTime = orderTime;
        this.countDownTime = countDownTime;
        this.orderUserName = orderUserName;
        this.userBuyCoinNum = userBuyCoinNum;
    }


    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public long getCountDownTime() {
        return countDownTime;
    }

    public void setCountDownTime(long countDownTime) {
        this.countDownTime = countDownTime;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getUserBuyCoinNum() {
        return userBuyCoinNum;
    }

    public void setUserBuyCoinNum(String userBuyCoinNum) {
        this.userBuyCoinNum = userBuyCoinNum;
    }

    @Override
    public String toString() {
        return "PendingOrderBean{" +
                "orderTime='" + orderTime + '\'' +
                ", countDownTime='" + countDownTime + '\'' +
                ", orderUserName='" + orderUserName + '\'' +
                ", userBuyCoinNum='" + userBuyCoinNum + '\'' +
                '}';
    }

}
