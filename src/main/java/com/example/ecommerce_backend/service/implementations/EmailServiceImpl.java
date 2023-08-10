package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.service.interfaces.EmailServiceInterface;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static org.antlr.v4.runtime.misc.Utils.readFile;

@Service
@Getter
@Setter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class EmailServiceImpl implements EmailServiceInterface {
    private final JavaMailSender mailSender;
    @Value("hostSender")
    private String hostSender;

    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    @Override
    public void sendEmailFromTemplate(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress(hostSender));
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);

        // Set the email's content to be the HTML template
        message.setContent(body, "text/html; charset=utf-8");

        mailSender.send(message);
    }
}
