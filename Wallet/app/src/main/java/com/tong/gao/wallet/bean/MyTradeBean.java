package com.tong.gao.wallet.bean;

import java.io.Serializable;
import java.util.List;

public class MyTradeBean implements Serializable {

    private String tradeId;
    private TradeStatus tradeStatus;
    private TradeStatus.TradeType tradeType;
    private String tradeTypeMoney;      //固额100 限额100
    private String repertoryNum;        //库存数量
    private String sellNum;             //销量
    private List<TradeStatus.TradePaymentType> paymentTypeList;
    private String publishTime;
    private String lastChangeTime;


    public MyTradeBean(String tradeId, TradeStatus tradeStatus, TradeStatus.TradeType tradeType, String tradeTypeMoney, String repertoryNum, String sellNum, List<TradeStatus.TradePaymentType> paymentTypeList, String publishTime, String lastChangeTime) {
        this.tradeId = tradeId;
        this.tradeStatus = tradeStatus;
        this.tradeType = tradeType;
        this.tradeTypeMoney = tradeTypeMoney;
        this.repertoryNum = repertoryNum;
        this.sellNum = sellNum;
        this.paymentTypeList = paymentTypeList;
        this.publishTime = publishTime;
        this.lastChangeTime = lastChangeTime;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public TradeStatus.TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeStatus.TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeTypeMoney() {
        return tradeTypeMoney;
    }

    public void setTradeTypeMoney(String tradeTypeMoney) {
        this.tradeTypeMoney = tradeTypeMoney;
    }

    public String getRepertoryNum() {
        return repertoryNum;
    }

    public void setRepertoryNum(String repertoryNum) {
        this.repertoryNum = repertoryNum;
    }

    public String getSellNum() {
        return sellNum;
    }

    public void setSellNum(String sellNum) {
        this.sellNum = sellNum;
    }

    public List<TradeStatus.TradePaymentType> getPaymentTypeList() {
        return paymentTypeList;
    }

    public void setPaymentTypeList(List<TradeStatus.TradePaymentType> paymentTypeList) {
        this.paymentTypeList = paymentTypeList;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(String lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }
}
