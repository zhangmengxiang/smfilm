package com.shimeng.smfilm.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResp<T> {
    private int status;
    private String message;
    private T data;
    private List<T> dataList;
    private long totalCount;

    public BaseResp(StatusMessage message){
        this.status = message.getCode();
        this.message = message.getMessage();
    }

    public BaseResp<T> setStatusMessage(StatusMessage message){
        this.status = message.getCode();
        this.message = message.getMessage();
        return this;
    }


    public int getStatus() {
        return status;
    }

    public BaseResp<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResp<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResp<T> setData(T data) {
        this.data = data;
        return this;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public BaseResp<T> setDataList(List<T> dataList) {
        this.dataList = dataList;
        return this;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public BaseResp<T> setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        return this;
    }
}
