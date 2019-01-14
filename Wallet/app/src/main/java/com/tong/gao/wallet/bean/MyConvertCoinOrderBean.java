package com.tong.gao.wallet.bean;

import java.io.Serializable;

public class MyConvertCoinOrderBean implements Serializable {
    private String  convertOrderId;
    private String  convertResult;
    private MyConvertCoinOrderBeanStatus  orderStatus;

    private final static  int handling= 1;
    private final static  int remitted= 2;      //汇出
    private final static  int rejected= 3;      //驳回


    public MyConvertCoinOrderBean(String convertOrderId, String convertResult, MyConvertCoinOrderBeanStatus orderStatus) {
        this.convertOrderId = convertOrderId;
        this.convertResult = convertResult;
        this.orderStatus = orderStatus;
    }

    public MyConvertCoinOrderBeanStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(MyConvertCoinOrderBeanStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getConvertOrderId() {
        return convertOrderId;
    }

    public void setConvertOrderId(String convertOrderId) {
        this.convertOrderId = convertOrderId;
    }

    public String getConvertResult() {
        return convertResult;
    }

    public void setConvertResult(String convertResult) {
        this.convertResult = convertResult;
    }

    @Override
    public String toString() {
        return "MyConvertCoinOrderBean{" +
                "convertOrderId='" + convertOrderId + '\'' +
                ", convertResult='" + convertResult + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

    public enum MyConvertCoinOrderBeanStatus{
        Handling(handling),Remitted(remitted),Rejected(rejected);
        int status;

        MyConvertCoinOrderBeanStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }
}
