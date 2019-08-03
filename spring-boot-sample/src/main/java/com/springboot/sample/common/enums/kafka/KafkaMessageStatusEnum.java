package com.springboot.sample.common.enums.kafka;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 15:05
 * @Description: kafka message表状态值
 */
public enum KafkaMessageStatusEnum {

    NOT_COMPLETE(0, "未完成"),
    COMPLETE(1, "已完成"),
    FAIL(2, "失败");

    private int code;
    private String text;


    KafkaMessageStatusEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
