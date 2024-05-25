package com.example.ecommerce_backend.service.implementations;

import static com.example.ecommerce_backend.constant.OrderStatus.TRACKING;

import com.example.ecommerce_backend.exception.EmailSendingException;
import com.example.ecommerce_backend.model.OrderEntity;
import com.example.ecommerce_backend.model.OrderItemEntity;
import com.example.ecommerce_backend.model.UserEntity;
import com.example.ecommerce_backend.service.interfaces.EmailServiceInterface;
import com.google.common.io.Files;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

@Service
@Async
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EmailServiceImpl implements EmailServiceInterface {

  private final JavaMailSender mailSender;

  @Value("${hostSender}")
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
  public void sendWelcomeEmail(@NotNull UserEntity userEntity) {
    String htmlTemplate = getEmailTemplate("welcome_email.html");

    htmlTemplate = htmlTemplate.replace("[Customer's Name]", userEntity.getFirstName());

    sendEmailFromTemplate(
        userEntity.getEmail(), "User successfully registered to ecommerce app", htmlTemplate);
  }

  @Override
  public void sendReceiptEmail(OrderEntity orderEntity) {
    String htmlTemplate =
        fillingOrderTemplateEmailDetail(getEmailTemplate("receipt_email.html"), orderEntity);

    sendEmailFromTemplate(
        orderEntity.getEmail(),
        String.format("Receipt for order id: %s", orderEntity.getId()),
        htmlTemplate);
  }

  @Override
  public void sendApprovedOrderEmail(List<OrderEntity> orderEntityList) {
    orderEntityList.forEach(
        orderEntity -> {
          if (orderEntity.getStatus() == TRACKING) {
            String htmlTemplate =
                fillingOrderTemplateEmailDetail(
                    getEmailTemplate("order_approved_email.html"), orderEntity);
            sendEmailFromTemplate(
                orderEntity.getEmail(),
                String.format("Order id: %s has been approved", orderEntity.getId()),
                htmlTemplate);
          } else {
            String htmlTemplate =
                fillingOrderTemplateEmailDetail(
                    getEmailTemplate("order_cancelled_email.html"), orderEntity);
            sendEmailFromTemplate(
                orderEntity.getEmail(),
                String.format("Order id: %s has been cancelled", orderEntity.getId()),
                htmlTemplate);
          }
        });
  }

  private String getEmailTemplate(String templateName) {
    try {
      File file = ResourceUtils.getFile(String.format("classpath:templates/%s", templateName));
      return Files.asCharSource(file, StandardCharsets.UTF_8).read();
    } catch (IOException ex) {
      log.error(ex.toString());
      throw new EmailSendingException(ex.toString());
    }
  }

  private String fillingOrderTemplateEmailDetail(String htmlTemplate, OrderEntity orderEntity) {
    htmlTemplate =
        htmlTemplate
            .replace("[Customer's Name]", orderEntity.getEmail())
            .replace("[Order Number]", String.valueOf(orderEntity.getId()))
            .replace("[Order Date]", orderEntity.getCreatedAt().toString())
            .replace("[Total Amount]", orderEntity.getGrandTotal())
            .replace("[Status]", orderEntity.getStatus().name());

    for (OrderItemEntity orderItemEntity : orderEntity.getOrderItemEntityList()) {
      htmlTemplate =
          htmlTemplate
              .replace(
                  "[Product Name]", orderItemEntity.getItemEntity().getProductEntity().getName())
              .replace("[SKU]", orderItemEntity.getItemEntity().getSku())
              .replace("[Product Quantity]", String.valueOf(orderItemEntity.getQuantity()))
              .replace("[Product Price]", orderItemEntity.getItemEntity().getPrice());
    }

    return htmlTemplate;
  }

  private void sendEmailFromTemplate(String to, String subject, String body) {
    try {
      MimeMessage message = mailSender.createMimeMessage();

      message.setFrom(new InternetAddress(hostSender));
      message.setRecipients(MimeMessage.RecipientType.TO, to);
      message.setSubject(subject);

      // Set the email's content to be the HTML template
      message.setContent(body, "text/html; charset=utf-8");

      mailSender.send(message);
    } catch (MessagingException ex) {
      log.error(ex.toString());
      throw new EmailSendingException(ex.toString());
    }
  }
}
