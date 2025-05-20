package com.ai.common.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

/**
 * @author kiturone
 * @date 2025/5/18 11:01
 */
@Data
public class WebResult {

    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE = -1;

    @JsonProperty("status_code")
    private Integer statusCode;

    /**
     * 状态信息
     */
    @JsonProperty("status_msg")
    private String statusMsg;

    /**
     * 数据
     */
    private Object data;

    public static WebResult buildSuccess(Object data) {
        return new WebResult(SUCCESS_CODE, "success", data);
    }

    public static WebResult success() {
        return new WebResult(SUCCESS_CODE, "success", new HashMap<>());
    }

    public static WebResult buildFail(Object data) {
        return new WebResult(ERROR_CODE, "error", data);
    }

    public WebResult(Integer statusCode, String statusMsg, Object data) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.data = data;
    }
}
