package com.example.springboot;

import com.example.springboot.service.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lucifer
 * @data 2018/5/9
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendMailTest(){
        mailService.sendSimpleMail("jlj_vip98@qq.com","主题：springboot验证码","登录验证码是：0808");
    }
}
