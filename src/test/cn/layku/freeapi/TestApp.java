package cn.layku.freeapi;

import cn.layku.freeapi.service.mail.MailService;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @packageName: cn.layku.freeapi
 * @name: TestApp
 * @description:
 * @author: 董定卓
 * @dateTime: 2020/03/07 12:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class TestApp {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void jasyptTest() {
        //加密
        String a = stringEncryptor.encrypt("123456");
        System.out.println(a);

    }

    @Resource(name = "aliMail")
    MailService mailService;

    @Test
    public void mailTest() {
        //测试发送邮件
        mailService.sendTextMail("dong.it@qq.com", "测试邮件", "测试文本邮件");
    }
}
