package com.tong.gao.wallet.bean;

import com.tong.gao.wallet.constants.MyConstant;

import java.io.Serializable;

public enum TradeStatus implements Serializable {

    ALL(MyConstant.Status_Trade_All),
    OnOffering(MyConstant.Status_Trade_OnOffering),
    SellOut(MyConstant.Status_Trade_SellOut),
    SoldOut(MyConstant.Status_Trade_SoldOut);


    int	state;

    TradeStatus(int state) {
        this.state = state;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public enum TradeType implements Serializable{
        ConstantDenomination(MyConstant.Status_Trade_ConstantDenomination),
        LimitDenomination(MyConstant.Status_Trade_LimitDenomination);

        int tradeType;
        TradeType(int tradeType){
            this.tradeType = tradeType;
        }

        public int getTradeType() {
            return tradeType;
        }

        public void setTradeType(int tradeType) {
            this.tradeType = tradeType;
        }
    }

    public enum TradePaymentType implements Serializable{
        PaymentZFB(MyConstant.Payment_ZFB),
        PaymentWechat(MyConstant.Payment_Wechat),
        PaymentBank(MyConstant.Payment_Bank);

        int paymentType;
        TradePaymentType(int paymentType){
            this.paymentType = paymentType;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }
    }

 }
