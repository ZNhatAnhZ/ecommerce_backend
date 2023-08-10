package com.example.ecommerce_backend.service.interfaces;

import jakarta.mail.MessagingException;

public interface EmailServiceInterface {
    void sendEmail(String to, String subject, String body);
    void sendEmailFromTemplate(String to, String subject, String body) throws MessagingException;
}
