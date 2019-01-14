package com.tong.gao.wallet.bean;

import java.io.Serializable;

public class MyOrderBean implements Serializable {

    private OrderStatus orderStatus;
    private String buyerName;
    private String buyCoinNum;
    private PaymentWay paymentType;
    private String shouldGetMoney;
    private String orderTime;
    private String orderCode;

    public MyOrderBean(OrderStatus orderStatus, String buyerName, String buyCoinNum, PaymentWay paymentType, String shouldGetMoney, String orderTime, String orderCode) {
        this.orderStatus = orderStatus;
        this.buyerName = buyerName;
        this.buyCoinNum = buyCoinNum;
        this.paymentType = paymentType;
        this.shouldGetMoney = shouldGetMoney;
        this.orderTime = orderTime;
        this.orderCode = orderCode;
    }



    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyCoinNum() {
        return buyCoinNum;
    }

    public void setBuyCoinNum(String buyCoinNum) {
        this.buyCoinNum = buyCoinNum;
    }

    public PaymentWay getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentWay paymentType) {
        this.paymentType = paymentType;
    }

    public String getShouldGetMoney() {
        return shouldGetMoney;
    }

    public void setShouldGetMoney(String shouldGetMoney) {
        this.shouldGetMoney = shouldGetMoney;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return "MyOrderBean{" +
                "orderStatus=" + orderStatus +
                ", buyerName='" + buyerName + '\'' +
                ", buyCoinNum='" + buyCoinNum + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", shouldGetMoney='" + shouldGetMoney + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderCode='" + orderCode + '\'' +
                '}';
    }






}
