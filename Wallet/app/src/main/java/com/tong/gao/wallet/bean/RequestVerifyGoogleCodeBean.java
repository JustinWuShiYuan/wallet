package com.tong.gao.wallet.bean;

import java.io.Serializable;

public class RequestVerifyGoogleCodeBean implements Serializable {
    private String code;
    private String type;


    public RequestVerifyGoogleCodeBean(String code, String type) {
        this.code = code;
        this.type = type;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RequestVerifyGoogleCodeBean{" +
                "code='" + code + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
