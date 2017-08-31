package com.wtzn.qy.qydevm.bean;

/**
 * baijw
 */
public class BaseMsg {
    private boolean success;
    private String content;

    public BaseMsg() {
    }

    public BaseMsg(boolean success, String content) {
        this.success = success;
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
