package com.tong.gao.wallet.bean;

import java.io.Serializable;

public class MyMessageItemBean implements Serializable {

    private String  headUrl;
    private String  userName;
    private String  messageContent;
    private String  messageTime;
    private boolean isRead;
    private String  messageCount;


    public MyMessageItemBean(String headUrl, String userName, String messageContent, String messageTime, boolean isRead, String messageCount) {
        this.headUrl = headUrl;
        this.userName = userName;
        this.messageContent = messageContent;
        this.messageTime = messageTime;
        this.isRead = isRead;
        this.messageCount = messageCount;
    }


    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }


    @Override
    public String toString() {
        return "MyMessageItemBean{" +
                "headUrl='" + headUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageTime='" + messageTime + '\'' +
                ", isRead=" + isRead +
                ", messageCount='" + messageCount + '\'' +
                '}';
    }
}
