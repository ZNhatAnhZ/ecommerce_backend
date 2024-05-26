package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.model.dto.paypalbusinessaccount.PaypalBusinessAccountCreateDto;
import com.example.ecommerce_backend.model.dto.paypalbusinessaccount.PaypalBusinessAccountUpdateDto;
import com.example.ecommerce_backend.model.entity.PaypalBusinessAccountEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaypalBusinessAccountInterface {

  PaypalBusinessAccountEntity addPaypalBusinessAccount(
      @NotNull PaypalBusinessAccountCreateDto paypalBusinessAccountCreateDto);

  Page<PaypalBusinessAccountEntity> getAllPaypalBusinessAccounts(Pageable pageable);

  PaypalBusinessAccountEntity getOldestPaypalBusinessAccount();

  PaypalBusinessAccountEntity updatePaypalBusinessAccount(
      @NotNull PaypalBusinessAccountUpdateDto paypalBusinessAccountUpdateDto);

  void deletePaypalBusinessAccount(int id);
}
