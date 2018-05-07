package com.example.SpringBoot.utils.http;

/**
 * @author lingjun.jlj
 * @create 2017-11-23
 **/
public enum ResponseCodeEnum {
    Success(0, "请求成功"),
    TOKEN_EXPIRED(99, "token过期"),
    VERIFY_CODE_ERROR(109, "验证码错误"),
    PHONE_CODE_ERROR(110, "手机号码格式错误"),
    Fail(1000, "请求失败"),
    Sign_failed(1002, "签名失败"),
    RSA_failed(1003, "RSA运算失败"),
    CRYPTO_ERROR(1004, "加密解密错误"),
    Opt_limited(1005, "操作次数限制"),
    LOGIN_OTHER(1006, "用户已在其它设备登录"),
    ILLEGAL_TOKEN(1007, "非法的token"),
    Authentication_failed(1010, "用户身份验证失败"),

    USER_NOT_REGISTERED(2001, "用户未注销"),
    USER_PASSWORD_ERROR(2002, "用户账号密码错误"),
    Frequent_Operation(2003, "操作频繁"),


    ILLEGAL_PARAM_MOBILE_FORMAT(4001, "手机号码格式不合法"),
    ILLEGAL_PARAM_AUTHCODE(4002, "验证码不正确"),
    ACCOUNT_REGISTERED(4003, "账号已经注册"),
    USER_NOT_EXIST(4004, "用户不存在"),
    AUTHCODE_TIME_OUT(4005, "验证码超时"),
    USER_NOT_ACTIVED(4006, "用户未激活"),
    ILLEGAL_INVITE_CODE(4007, "邀请码错误"),
    ILLEGAL_PASSWORD(4008, "密码错误"),
    DIRTY_WORD(4009, "脏词"),
    PARAMETER_ERROR(4010, "参数异常"),
    USER_ACTIVED(4010, "用户已激活"),
    PROJECT_NAME_REPEAT(4011, "项目名重复"),
    PERMISSION_DEND(4015, "没有权限访问"),
    CROSS_AUTHORITY(4015, "跨权请求"),
    PASSWORD_NOT_SET(4016, "密码未设置"),
    FAIL_GO_TO_ALIPAY(40617, "fail go to alipay"),
    FAIL_CLOSE_CONSULT(4018, "fail close consult"),
    FAIL_CLOSE_CONSULT_TASK_IN_SERVICE(4019, "fail close consult task in service"),
    FAIL_FINISH_SERVICE_FOR_BILLING(4020, "fail finish service for billing"),

    SERVER_ERROR(5001, "服务端错误"),
    API_EXPIRED(5002, "api过期"),
    NO_DATA(5003, "查无数据"),
    DOT_SUBMIT_REPEAT(5004, "dot submit repeat"),
    BAD_DATA(5005, "无效数据"),
    DUPLICATE(5007, "数据重复"),
    FREQUENT_REQUEST(5008, "请求过于频繁"),
    PARAM_ERROR(5009, "参数错误"),

    APPVERSION_LOWER_ERROR(7001, "APP版本过低"),

    UPLOAD_FAIL(8001, "文件上传失败");


    private int code;
    private String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
