package com.springboot.sample.service.mail.impl;

import com.springboot.sample.service.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author kiturone
 * @data 2018/5/9
 * @Description:
 */
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Autowired
    //private JavaMailSender javaMailSender;

    //@Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(content);
//
//        try {
//            javaMailSender.send(message);
//            logger.info("简单邮件已经发送。");
//        } catch (Exception e) {
//            logger.error("发送简单邮件时发生异常！", e);
//        }

    }
}
