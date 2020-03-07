package cn.layku.freeapi.service.mail;

import java.util.Map;

/**
 * @packageName: cn.layku.freeapi.service
 * @name: MailService
 * @description:
 * @author: 董定卓
 * @dateTime: 2020/03/07 15:38
 */
public interface MailService {

    /**
     * 发送纯文本邮件
     *
     * @param to
     * @param title
     * @param content
     */
    void sendTextMail(String to, String title, String content);

    /**
     * 发送的邮件是富文本（附件，图片，html等）
     *
     * @param to
     * @param title
     * @param content
     * @param attachment
     */
    void sendHtmlMail(String to, String title, String content, Map<String, Object> attachment);

    /**
     * 发送模板邮件 使用thymeleaf模板
     *
     * @param to
     * @param title
     * @param attachment
     * @param template
     */
    void sendTemplateMail(String to, String title, String template, Map<String, Object> attachment);

}
