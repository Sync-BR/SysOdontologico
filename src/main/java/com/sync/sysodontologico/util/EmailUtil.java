package com.sync.sysodontologico.util;

import com.sync.sysodontologico.config;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        config config = new config();
        message.setTo(to);
        message.setSubject(config.title);
        message.setText(config.text+config.linkRegister+token);
        try {
            mailSender.send(message);
        } catch (MailSendException e) {
            e.printStackTrace();
        }
    }

}

