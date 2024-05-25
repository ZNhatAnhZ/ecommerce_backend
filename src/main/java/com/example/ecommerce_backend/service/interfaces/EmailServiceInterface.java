package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.model.OrderEntity;
import com.example.ecommerce_backend.model.UserEntity;

import java.util.List;

public interface EmailServiceInterface {

  void sendEmail(String to, String subject, String body);

  void sendWelcomeEmail(UserEntity userEntity);

  void sendReceiptEmail(OrderEntity orderEntity);

  void sendApprovedOrderEmail(List<OrderEntity> orderEntityList);

}
