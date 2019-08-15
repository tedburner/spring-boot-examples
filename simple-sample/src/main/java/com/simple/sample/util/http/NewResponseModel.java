package com.simple.sample.util.http;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author Lucifer
 * @create 2017-11-23
 * https 数据返回包装
 **/
public class NewResponseModel<T> implements Serializable {
    private static final long serialVersionUID = -4688208407174044501L;

    private int status;
    private String info;
    private T data;
    private long timeStamp = System.currentTimeMillis();

    public static <T> NewResponseModel<T> Success() {
        return new NewResponseModel<>(ResponseCodeEnum.Success.getCode(), ResponseCodeEnum.Success.getMessage());
    }

    public static <T> NewResponseModel<T> ParamError(String info) {
        return new NewResponseModel<T>(ResponseCodeEnum.PARAMETER_ERROR.getCode(), info);
    }

    public static <T> NewResponseModel<T> ParamError() {
        return new NewResponseModel<T>(ResponseCodeEnum.PARAMETER_ERROR.getCode(), ResponseCodeEnum.PARAMETER_ERROR.getMessage());
    }

    public static <T> NewResponseModel<T> NoPerMission(String info) {
        return new NewResponseModel<T>(ResponseCodeEnum.PERMISSION_DEND.getCode(), info);
    }

    public static <T> NewResponseModel<T> Fail(String info) {
        return new NewResponseModel<T>(ResponseCodeEnum.Fail.getCode(), StringUtils.defaultIfBlank(info, "请求失败"));
    }

    public static <T> NewResponseModel<T> Fail() {
        return new NewResponseModel<>(ResponseCodeEnum.Fail.getCode(), ResponseCodeEnum.Fail.getMessage());
    }

    public static <T> NewResponseModel LoginError() {
        return new NewResponseModel<T>(ResponseCodeEnum.LOGIN_FAIL.getCode(), ResponseCodeEnum.LOGIN_FAIL.getMessage());
    }

    public NewResponseModel() {

    }

    public NewResponseModel(int status) {
        this.status = status;
    }

    public NewResponseModel(int status, String info) {
        this.info = info;
        this.status = status;
    }

    public NewResponseModel(ResponseCodeEnum responseCodeEnum) {
        this.info = responseCodeEnum.getMessage();
        this.status = responseCodeEnum.getCode();
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
