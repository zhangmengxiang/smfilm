package com.shimeng.smfilm.common;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum StatusMessage {
    SUCCESS(1001,"成功"),
    ERROR(1005,"未知错误"),
    DATA_EMPTY(1004,"数据为空"),
    DATA_REPEAT(1006,"数据重复"),
    ILLEGAL_CONTENT(1008,"内容不合法"),
    URL_ERROR(1002,"url错误"),
    NOT_LOGIN(1009,"用户未登录"),
    PARAMS(1003,"参数错误");
    private int code;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }
}