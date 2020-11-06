package com.zhaops.mlchecksvc.user.dto;

/**
 * @author SoYuan
 */
public class ResultModel<T> {
    private int code;
    private String msg;
    private T Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
