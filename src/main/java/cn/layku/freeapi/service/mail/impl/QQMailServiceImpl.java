package cn.layku.freeapi.service.mail.impl;

import cn.layku.freeapi.service.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @packageName: cn.layku.freeapi.service.mail.impl
 * @name: QQMailServiceImpl
 * @description: QQ邮箱
 * @author: 董定卓
 * @dateTime: 2020/03/07 15:41
 */
@Service("qqMail")
public class QQMailServiceImpl implements MailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    String from;

    /**
     * 发送纯文本邮件
     *
     * @param to
     * @param title
     * @param content
     */
    @Override
    public void sendTextMail(String to, String title, String content) {
        //建立邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(title);
        message.setTo(to);
        message.setText(content);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            logger.error("纯文本邮件发送失败->message:{}", e.getMessage());
        }
    }

    /**
     * 发送的邮件是富文本（附件，图片，html等）
     *
     * @param to
     * @param title
     * @param content
     * @param attachment
     */
    @Override
    public void sendHtmlMail(String to, String title, String content, Map<String, Object> attachment) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            //是否发送的邮件是富文本（附件，图片，html等）
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(title);
            messageHelper.setText(content, true);//false，显示原始html代码，无效果
            //判断是否有附加图片等
            if (attachment != null && attachment.size() > 0) {
                attachment.entrySet().stream().forEach(entrySet -> {
                    try {
                        File file = new File(String.valueOf(entrySet.getValue()));
                        if (file.exists()) {
                            messageHelper.addAttachment(entrySet.getKey(), new FileSystemResource(file));
                        }
                    } catch (MessagingException e) {
                        logger.error("附件发送失败->message:{}", e.getMessage());
                    }
                });
            }
            //发送
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("富文本邮件发送失败->message:{}", e.getMessage());
        }
    }

    /**
     * 发送模板邮件 使用thymeleaf模板
     *
     * @param to
     * @param title
     * @param template
     * @param attachment
     */
    @Override
    public void sendTemplateMail(String to, String title, String template, Map<String, Object> attachment) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(title);
            //使用模板thymeleaf
            //Context是导这个包import org.thymeleaf.context.Context;
            Context context = new Context();
            //定义模板数据
            context.setVariables(attachment);
            //获取thymeleaf的html模板
            //String template = "/template/mail";
            String emailContent = templateEngine.process(template, context); //指定模板路径
            messageHelper.setText(emailContent, true);
            //发送邮件
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("模板邮件发送失败->message:{}", e.getMessage());
        }
    }
}
