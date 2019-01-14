package com.tong.gao.wallet.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String userid;
    private String realname;
    private String istrpwd;   //是否已设置交易密码
    private String vailidnumber;       //是否实名认证
    private String status;
    private String googlesecret;
    private String valigooglesecret;        //是否google验证
    private String safeverifyswitch;      //是否开启google验证
    private String userType;
    private String nickname;
    private String username;

    public UserInfo(String userid, String realname, String istrpwd, String vailidnumber, String status, String googlesecret, String valigooglesecret, String safeverifyswitch, String userType, String nickname, String username) {
        this.userid = userid;
        this.realname = realname;
        this.istrpwd = istrpwd;
        this.vailidnumber = vailidnumber;
        this.status = status;
        this.googlesecret = googlesecret;
        this.valigooglesecret = valigooglesecret;
        this.safeverifyswitch = safeverifyswitch;
        this.userType = userType;
        this.nickname = nickname;
        this.username = username;
    }

    public UserInfo(String userid, String istrpwd, String vailidnumber, String status, String valigooglesecret, String safeverifyswitch, String userType, String nickname, String username) {
        this.userid = userid;
        this.istrpwd = istrpwd;
        this.vailidnumber = vailidnumber;
        this.status = status;
        this.valigooglesecret = valigooglesecret;
        this.safeverifyswitch = safeverifyswitch;
        this.userType = userType;
        this.nickname = nickname;
        this.username = username;
    }

    //    public UserInfo(String userid, String realname, String istrpwd, String vailidnumber, String status, String googlesecret, String valigooglesecret, String safeverifyswitch, String userType) {
//        this.userid = userid;
//        this.realname = realname;
//        this.istrpwd = istrpwd;
//        this.vailidnumber = vailidnumber;
//        this.status = status;
//        this.googlesecret = googlesecret;
//        this.valigooglesecret = valigooglesecret;
//        this.safeverifyswitch = safeverifyswitch;
//        this.userType = userType;
//    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIstrpwd() {
        return istrpwd;
    }

    public void setIstrpwd(String istrpwd) {
        this.istrpwd = istrpwd;
    }

    public String getVailidnumber() {
        return vailidnumber;
    }

    public void setVailidnumber(String vailidnumber) {
        this.vailidnumber = vailidnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGooglesecret() {
        return googlesecret;
    }

    public void setGooglesecret(String googlesecret) {
        this.googlesecret = googlesecret;
    }

    public String getValigooglesecret() {
        return valigooglesecret;
    }

    public void setValigooglesecret(String valigooglesecret) {
        this.valigooglesecret = valigooglesecret;
    }

    public String getSafeverifyswitch() {
        return safeverifyswitch;
    }

    public void setSafeverifyswitch(String safeverifyswitch) {
        this.safeverifyswitch = safeverifyswitch;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "userid='" + userid + '\'' +
                ", realname='" + realname + '\'' +
                ", istrpwd='" + istrpwd + '\'' +
                ", vailidnumber='" + vailidnumber + '\'' +
                ", status='" + status + '\'' +
                ", googlesecret='" + googlesecret + '\'' +
                ", valigooglesecret='" + valigooglesecret + '\'' +
                ", safeverifyswitch='" + safeverifyswitch + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
