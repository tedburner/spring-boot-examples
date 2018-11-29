package com.sample.springboot.service.mail;

/**
 * @author Lucifer
 * @data 2018/5/9
 * @Description: 邮件接口
 */
public interface MailService {

    void sendSimpleMail(String to, String subject, String content);

}
