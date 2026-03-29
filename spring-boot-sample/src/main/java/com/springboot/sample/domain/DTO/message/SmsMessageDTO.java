package com.springboot.sample.domain.DTO.message;

import java.io.Serializable;

/**
 * @author: kiturone
 * @date: 2018/11/12 19:34
 * @description:
 */
public class SmsMessageDTO implements Serializable {

    /**
     * 接收人手机号码
     */
    private String phone;

    /**
     * 模板code
     */
    private String templateCode;

    private Long kafkaMessageId;

    /**
     * 模板参数
     */
    private String templateParam;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Long getKafkaMessageId() {
        return kafkaMessageId;
    }

    public void setKafkaMessageId(Long kafkaMessageId) {
        this.kafkaMessageId = kafkaMessageId;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    @Override
    public String toString() {
        return "SMSMessageDTO{" +
                "phone='" + phone + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", kafkaMessageId=" + kafkaMessageId +
                ", templateParam='" + templateParam + '\'' +
                '}';
    }
}
