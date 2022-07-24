package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
public class MailController {
    @Autowired(required = false)
    JavaMailSenderImpl sender;

    /**
     *
     * @param html MimeMessageHelper 的参数
     * @param subject 邮件主题
     * @param text 邮件文本内容
     * @param attachmentName 附件名
     * @param attachmentPath 附件的绝对文件路径
     * @param mailReceiver 收信人
     * @param mailSender 发件人
     * @return 字符串提示信息："邮件发送成功！"
     * @throws MessagingException 可能返回的异常
     */
    @RequestMapping("/sendMail")
    public String sendMail(Boolean html, String subject, String text, String attachmentName, String attachmentPath, String mailReceiver, String mailSender) throws MessagingException {
        // 设置并发送一个复杂的邮件
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, html);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
        mimeMessageHelper.addAttachment(attachmentName, new File(attachmentPath));  // 附件
        mimeMessageHelper.setTo(mailReceiver);
        mimeMessageHelper.setFrom(mailSender);
        sender.send(mimeMessage);
        return "邮件发送成功！";
    }
}
