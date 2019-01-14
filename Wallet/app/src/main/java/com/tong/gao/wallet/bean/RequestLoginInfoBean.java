package com.tong.gao.wallet.bean;

import java.io.Serializable;

public class RequestLoginInfoBean implements Serializable {

    private String loginname;
    private String pwd;

    public RequestLoginInfoBean(String loginname, String pwd) {
        this.loginname = loginname;
        this.pwd = pwd;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "RequestLoginInfoBean{" +
                "loginname='" + loginname + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
