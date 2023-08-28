package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.exception.EmailSendingException;
import com.example.ecommerce_backend.model.UserEntity;
import com.example.ecommerce_backend.service.interfaces.EmailServiceInterface;
import com.google.common.io.Files;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
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

    @Async
    public void sendWelcomeEmail(UserEntity userEntity) {
        try {
            File file = ResourceUtils.getFile("classpath:welcome_email.html");
            String htmlTemplate = Files.asCharSource(file, StandardCharsets.UTF_8).read();

            // Replace placeholders in the HTML template with dynamic values
            htmlTemplate = htmlTemplate.replace("[Customer's Name]", userEntity.getFirstName());
            sendEmailFromTemplate(userEntity.getEmail(), "User successfully registered to ecommerce app", htmlTemplate);

        } catch (IOException | MessagingException ex) {
            log.error(ex.toString());
            throw new EmailSendingException(ex.toString());
        }
    }
}
