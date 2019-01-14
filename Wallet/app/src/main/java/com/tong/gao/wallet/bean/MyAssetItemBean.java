package com.tong.gao.wallet.bean;

import java.io.Serializable;

public class MyAssetItemBean implements Serializable {
    private String  orderId;
    private String  assetTradeTitle;
    private String  tradeTime;
    private String  tradeCoinNum;
    private boolean isAdd;

    public MyAssetItemBean(String orderId, String assetTradeTitle, String tradeTime, String tradeCoinNum, boolean isAdd) {
        this.orderId = orderId;
        this.assetTradeTitle = assetTradeTitle;
        this.tradeTime = tradeTime;
        this.tradeCoinNum = tradeCoinNum;
        this.isAdd = isAdd;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAssetTradeTitle() {
        return assetTradeTitle;
    }

    public void setAssetTradeTitle(String assetTradeTitle) {
        this.assetTradeTitle = assetTradeTitle;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTradeCoinNum() {
        return tradeCoinNum;
    }

    public void setTradeCoinNum(String tradeCoinNum) {
        this.tradeCoinNum = tradeCoinNum;
    }

    @Override
    public String toString() {
        return "MyAssetItemBean{" +
                "orderId='" + orderId + '\'' +
                ", assetTradeTitle='" + assetTradeTitle + '\'' +
                ", tradeTime='" + tradeTime + '\'' +
                ", tradeCoinNum='" + tradeCoinNum + '\'' +
                '}';
    }
}
