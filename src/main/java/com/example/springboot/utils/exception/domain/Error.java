package com.example.springboot.utils.exception.domain;

import java.io.Serializable;

/**
 * @author: Lucifer
 * @date: 2018/8/15 15:21
 * @description: 错误常量
 */
public class Error implements Serializable {
    private static final long serialVersionUID = 7390194766842293132L;

    public static final Integer DefaultErrorCode = -1;

    /**
     * 错误状态
     */
    private Integer status;
    /**
     * 错误吗
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 开发者使用的错误信息,如 堆栈信息,排除 第三方类库的错误,只保留自己代码的信息
     */
    private String developerMessage;
    /**
     * 给定一个URL,此错误的详细信息,产生原因,解决办法等等
     */
    private String moreInfo;
    /**
     * 请求的ID号,后台可以根据此ID号 查到所有相关日志
     * 需要后台配合使用,暂时没有启用
     */
    private String requestId;

    /**
     * 完整构造器
     *
     * @param status           状态码
     * @param code             错误码
     * @param message          错误信息
     * @param developerMessage 开发者错误信息
     * @param moreInfo         url地址
     * @param requestId        请求id号
     */
    public Error(Integer status, Integer code, String message, String developerMessage, String moreInfo, String requestId) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
        this.moreInfo = moreInfo;
        this.requestId = requestId;
    }

    /**
     * 基础信息构造器0
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public Error(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 基础信息构造器1
     *
     * @param status  状态码
     * @param code    错误码
     * @param message 错误信息
     */
    public Error(Integer status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    /**
     * 基础构造器2
     *
     * @param status           状态码
     * @param code             错误码
     * @param message          错误信息
     * @param developerMessage 开发者错误信息
     */
    public Error(Integer status, Integer code, String message, String developerMessage) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "{\"status\":\"" + status + "\",\"code\":\"" + code + "\",\"message\":\"" + message + "\",\"developerMessage\":\"" + developerMessage + "\",\"moreInfo\":\"" + moreInfo + "\",\"requestId\":\"" + moreInfo + "\"}";
    }


    public static Error.ErrorBuilder builder() {
        return new Error.ErrorBuilder();
    }

    public static class ErrorBuilder {
        private Integer status;
        private Integer code;
        private String message;
        private String developerMessage;
        private String moreInfo;
        private String requestId;

        public ErrorBuilder() {
        }

        public Error.ErrorBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public Error.ErrorBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public Error.ErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Error.ErrorBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Error.ErrorBuilder moreInfo(String moreInfo) {
            this.moreInfo = moreInfo;
            return this;
        }

        public Error.ErrorBuilder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Error build() {
            return new Error(this.status, this.code, this.message, this.developerMessage, this.moreInfo, this.requestId);
        }
    }

}
