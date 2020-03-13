package cn.layku.freeapi;

import cn.layku.freeapi.service.ip.IpService;
import cn.layku.freeapi.service.mail.MailService;
import cn.layku.freeapi.service.spider.SpiderService;
import cn.layku.freeapi.service.weekday.WeekdayService;
import com.alibaba.fastjson.JSON;
import org.jasypt.encryption.StringEncryptor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

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

    private static Logger logger = LoggerFactory.getLogger(TestApp.class);

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


    @Resource(name = "pcOnlineIp")
    IpService ipService;

    @Test
    public void ipToAddrTest() throws Exception {
        String[] ips = new String[]{
                "61.132.163.68",
                "219.141.136.10",
                "61.128.192.68",
                "218.85.152.99",
                "202.100.64.68",
                "202.96.128.86",
                "202.103.225.68",
                "202.98.192.67",
                "222.88.88.88",
                "219.147.198.230",
                "202.103.24.68",
                "222.246.129.80",
                "218.2.2.2",
                "202.101.224.69",
                "219.146.1.66",
                "218.30.19.40",
                "202.96.209.5",
                "61.139.2.69",
                "219.150.32.132",
                "222.172.200.68"
        };

        for (String ip : ips) {
            Map<String, Object> ipAddr = ipService.getIpAddr(ip);
            System.out.println(JSON.toJSONString(ipAddr));
        }

    }


    @Resource(name = "weekday")
    WeekdayService weekdayService;

    @Test
    public void weekdayTest() {
        String a = "2018-02-15";
        boolean holiday = weekdayService.isHoliday(a);
        System.out.println(holiday);
    }

    @Resource(name = "addressSpider")
    SpiderService spiderService;

    @Test
    public void addressSpiderTest() {
        Object data = spiderService.getData();
        System.out.println(JSON.toJSONString(data));

    }

}
