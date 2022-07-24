package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class DemoApplicationTests {
    @Autowired(required = false)
    JavaMailSenderImpl sender;

    @Test
    void contextLoads() {
        // 设置一个简单的邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("邮件测试");
        simpleMailMessage.setText("这是一个测试邮件。");
        simpleMailMessage.setTo("zhangruizhuo4444@163.com");
        simpleMailMessage.setFrom("570232054@qq.com");
        sender.send(simpleMailMessage);
    }

    @Test
    void contextLoadsComplexMail() throws MessagingException {
        // 设置一个复杂的邮件
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("复杂邮件测试");
        mimeMessageHelper.setText("<p style='color:red'>这是一个测试邮件。</p>", true);
        mimeMessageHelper.addAttachment("pic.jpg", new File("C:/Users/4444/Pictures/pic.jpg"));  // 附件
        mimeMessageHelper.setTo("zhangruizhuo4444@163.com");
        mimeMessageHelper.setFrom("570232054@qq.com");
        sender.send(mimeMessage);
    }

}
