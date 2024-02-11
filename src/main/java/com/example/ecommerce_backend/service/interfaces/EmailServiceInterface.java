package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.model.UserEntity;
import jakarta.mail.MessagingException;

public interface EmailServiceInterface {

  void sendEmail(String to, String subject, String body);

  void sendEmailFromTemplate(String to, String subject, String body) throws MessagingException;

  public void sendWelcomeEmail(UserEntity userEntity);
}
