package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.model.dto.paypalbusinessaccount.PaypalBusinessAccountCreateDto;
import com.example.ecommerce_backend.model.dto.paypalbusinessaccount.PaypalBusinessAccountIndexDto;
import com.example.ecommerce_backend.model.dto.paypalbusinessaccount.PaypalBusinessAccountUpdateDto;
import com.example.ecommerce_backend.model.mapper.paypalbusinessaccount.PaypalBusinessAccountIndexMapper;
import com.example.ecommerce_backend.service.interfaces.PaypalBusinessAccountInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/paypal-business-accounts")
@RestController
@Slf4j
@RequiredArgsConstructor
public class PaypalBusinessAccountController {

  private final PaypalBusinessAccountInterface paypalBusinessAccountInterface;

  private final PaypalBusinessAccountIndexMapper paypalBusinessAccountIndexMapper;

  @PostMapping
  public ResponseEntity<PaypalBusinessAccountIndexDto> createPaypalBusinessAccount(
      @RequestBody PaypalBusinessAccountCreateDto paypalBusinessAccountCreateDto) {
    return ResponseEntity.ok(
        paypalBusinessAccountIndexMapper.toDto(
            paypalBusinessAccountInterface.addPaypalBusinessAccount(
                paypalBusinessAccountCreateDto)));
  }

  @GetMapping
  public ResponseEntity<Page<PaypalBusinessAccountIndexDto>> getPaypalBusinessAccount(
      @PageableDefault(sort = "id") Pageable pageable) {
    return ResponseEntity.ok(
        paypalBusinessAccountInterface
            .getAllPaypalBusinessAccounts(pageable)
            .map(paypalBusinessAccountIndexMapper::toDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PaypalBusinessAccountIndexDto> updatePaypalBusinessAccount(
      @PathVariable int id,
      @RequestBody PaypalBusinessAccountUpdateDto paypalBusinessAccountUpdateDto) {
    paypalBusinessAccountUpdateDto.setId(id);
    return ResponseEntity.ok(
        paypalBusinessAccountIndexMapper.toDto(
            paypalBusinessAccountInterface.updatePaypalBusinessAccount(
                paypalBusinessAccountUpdateDto)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePaypalBusinessAccount(@PathVariable int id) {
    paypalBusinessAccountInterface.deletePaypalBusinessAccount(id);
    return ResponseEntity.ok().build();
  }
}
