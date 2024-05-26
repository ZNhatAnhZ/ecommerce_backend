package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.exception.ResourceDuplicateException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.exception.UnavailablePaymentException;
import com.example.ecommerce_backend.model.dto.paypalbusinessaccount.PaypalBusinessAccountCreateDto;
import com.example.ecommerce_backend.model.dto.paypalbusinessaccount.PaypalBusinessAccountUpdateDto;
import com.example.ecommerce_backend.model.entity.PaypalBusinessAccountEntity;
import com.example.ecommerce_backend.repository.PaypalBusinessAccountEntityRepository;
import com.example.ecommerce_backend.service.interfaces.PaypalBusinessAccountInterface;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaypalBusinessAccountServiceImpl implements PaypalBusinessAccountInterface {

  private final PaypalBusinessAccountEntityRepository paypalBusinessAccountEntityRepository;

  @Override
  public PaypalBusinessAccountEntity addPaypalBusinessAccount(
      @NotNull PaypalBusinessAccountCreateDto paypalBusinessAccountCreateDto) {
    if (paypalBusinessAccountEntityRepository.existsByEmail(
        paypalBusinessAccountCreateDto.getEmail())) {
      throw new ResourceDuplicateException(
          "Paypal business account already exists with email: "
              + paypalBusinessAccountCreateDto.getEmail());
    } else if (paypalBusinessAccountEntityRepository.existsByClientId(
        paypalBusinessAccountCreateDto.getClientId())) {
      throw new ResourceDuplicateException(
          "Paypal business account already exists with client id: "
              + paypalBusinessAccountCreateDto.getClientId());
    }

    PaypalBusinessAccountEntity paypalBusinessAccountEntity =
        PaypalBusinessAccountEntity.builder()
            .email(paypalBusinessAccountCreateDto.getEmail())
            .clientId(paypalBusinessAccountCreateDto.getClientId())
            .clientSecret(paypalBusinessAccountCreateDto.getClientSecret())
            .isEnable(paypalBusinessAccountCreateDto.getIsEnable())
            .build();
    return paypalBusinessAccountEntityRepository.save(paypalBusinessAccountEntity);
  }

  @Override
  public Page<PaypalBusinessAccountEntity> getAllPaypalBusinessAccounts(Pageable pageable) {
    return paypalBusinessAccountEntityRepository.findAll(pageable);
  }

  @Override
  public PaypalBusinessAccountEntity getOldestPaypalBusinessAccount() {
    Optional<PaypalBusinessAccountEntity> paypalBusinessAccountEntity =
        paypalBusinessAccountEntityRepository.findFirstByOrderByIdAsc();

    if (paypalBusinessAccountEntity.isEmpty()) {
      throw new UnavailablePaymentException("No Paypal business account available");
    }

    return paypalBusinessAccountEntity.get();
  }

  @Override
  public PaypalBusinessAccountEntity updatePaypalBusinessAccount(
      @NotNull PaypalBusinessAccountUpdateDto paypalBusinessAccountUpdateDto) {
    PaypalBusinessAccountEntity existingPaypalBusinessAccountEntity =
        paypalBusinessAccountEntityRepository
            .findById(paypalBusinessAccountUpdateDto.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Paypal business account not found"));

    Optional.ofNullable(paypalBusinessAccountUpdateDto.getEmail())
        .ifPresent(existingPaypalBusinessAccountEntity::setEmail);
    Optional.ofNullable(paypalBusinessAccountUpdateDto.getClientId())
        .ifPresent(existingPaypalBusinessAccountEntity::setClientId);
    Optional.ofNullable(paypalBusinessAccountUpdateDto.getClientSecret())
        .ifPresent(existingPaypalBusinessAccountEntity::setClientSecret);
    Optional.ofNullable(paypalBusinessAccountUpdateDto.getIsEnable())
        .ifPresent(existingPaypalBusinessAccountEntity::setIsEnable);

    return paypalBusinessAccountEntityRepository.save(existingPaypalBusinessAccountEntity);
  }

  @Override
  public void deletePaypalBusinessAccount(int id) {
    if (!paypalBusinessAccountEntityRepository.existsById(id)) {
      throw new ResourceNotFoundException(
          String.format("Paypal business account not found with id: %d", id));
    }

    paypalBusinessAccountEntityRepository.deleteById(id);
  }
}
