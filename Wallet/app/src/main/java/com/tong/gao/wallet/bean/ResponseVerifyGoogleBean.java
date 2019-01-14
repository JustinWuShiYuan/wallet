package com.tong.gao.wallet.bean;

import java.io.Serializable;

public class ResponseVerifyGoogleBean implements Serializable {

    private String err_code;
    private String msg;


    public ResponseVerifyGoogleBean(String err_code, String msg) {
        this.err_code = err_code;
        this.msg = msg;
    }


    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseVerifyGoogleBean{" +
                "err_code='" + err_code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
