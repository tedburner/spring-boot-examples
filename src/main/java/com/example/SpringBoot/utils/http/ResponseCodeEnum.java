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
    USER_NOT_ASSISTANT(2003, "用户不是私人助理"),
    USER_PASSWORD_ERROR(2004, "用户账号密码错误"),
    Frequent_Operation(2005, "操作频繁"),

    USER_NOT_CUSTOMER(3001, "用户不是壹宝用户"),

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
    ADD_DOCTOR_HOSPITAL_NOT_EXIST(4021, "医院信息不存在"),
    ADD_DOCTOR_HOSPITAL_DEPART_ERROR(4022, "科室信息不正确"),
    ADD_DOCTOR_ITEM_ERROR(4023, "类目信息不正确"),
    ADD_DOCTOR_TITLE_ERROR(4024, "职级信息不正确"),
    ADD_DOCTOR_PROFESS_RANK_ERROR(4025, "教学职称不正确"),
    ADD_DOCTOR_TYPE_ERROR(4026, "医生类型不正确"),
    ADD_DOCTOR_EXISTS(4027, "医生信息已被添加"),
    ADD_COMMODITY_NOT_EXISTS(4031, "服务信息不存在"),
    ADD_COMMODITY_REGISTERFEE_ERROR(4032, "挂号费有误"),
    ADD_COMMODITY_PRICE_ERROR(4033, "价格有误"),
    ADD_COMMODITY_DOCTOR_ERROR(4034, "请选择医生"),
    ADD_COMMODITY_AGREE_DOCTOR_EXISTS(4035, "协议医生"),
    ADD_COMMODITY_TYPE_ERROR(4036, "服务类型"),
    ADD_COMMODITY_SUBTYPE_ERROR(4037, "子服务类型"),
    ADD_COMMODITY_TIME_ERROR(4038, "服务时间"),
    SAVE_PATIENT_NAME_REPEAT(4041, "患者名字重复"),
    OBJECT_EXIST(4050, "目标对象已经存在"),
    FAIL_GO_TO_ALIPAY(4060, "fail go to alipay"),
    FAIL_CLOSE_CONSULT(4061, "fail close consult"),
    FAIL_CLOSE_CONSULT_TASK_IN_SERVICE(4062, "fail close consult task in service"),
    FAIL_FINISH_SERVICE_FOR_BILLING(4063, "fail finish service for billing"),

    SERVER_ERROR(5001, "服务端错误"),
    API_EXPIRED(5002, "api过期"),
    NO_DATA(5003, "查无数据"),
    DOT_SUBMIT_REPEAT(5004, "dot submit repeat"),
    BAD_DATA(5005, "无效数据"),
    DUPLICATE(5007, "数据重复"),
    FREQUENT_REQUEST(5008, "请求过于频繁"),
    PARAM_ERROR(5009, "参数错误"),
    PROCESS_ERROR(5010, "处理流程异常"),

    COMMODITY_ERROR(6001, "基础服务异常 "),
    COMMODITY_DEFIND(6002, "无效的基础服务"),
    COMMODITY_UNCOMPLETE(6003, "服务不是完成状态"),

    APPVERSION_LOWER_ERROR(7001, "app版本过低"),

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
