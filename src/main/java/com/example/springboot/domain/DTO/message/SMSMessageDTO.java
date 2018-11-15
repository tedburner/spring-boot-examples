package com.example.springboot.domain.DTO.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:34
 * @description:
 */
public class SMSMessageDTO implements Serializable {

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
