package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.model.dto.cartitementity.CartItemEntityCreateRequestDto;
import com.example.ecommerce_backend.model.dto.cartitementity.CartItemEntityIndexDto;
import com.example.ecommerce_backend.model.dto.cartitementity.CartItemEntityUpdateQuantityDto;
import com.example.ecommerce_backend.model.mapper.cartitementiy.CartItemEntityIndexMapper;
import com.example.ecommerce_backend.service.interfaces.CartServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/carts")
@RestController
@Slf4j
@RequiredArgsConstructor
public class CartController {

  private final CartServiceInterface cartServiceInterface;

  private final CartItemEntityIndexMapper cartItemEntityIndexMapper;

  @GetMapping("/{id}")
  public ResponseEntity<Page<CartItemEntityIndexDto>> getAllItemEntitiesFromCart(
      @PathVariable("id") int id,
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(
        cartServiceInterface
            .getAllItemEntitiesFromCart(id, pageable)
            .map(cartItemEntityIndexMapper::toDto));
  }

  @PostMapping("/{id}")
  public ResponseEntity<Page<CartItemEntityIndexDto>> addItemEntityToCart(
      @PathVariable int id,
      @RequestBody CartItemEntityCreateRequestDto cartItemEntityCreateRequestDto) {
    cartItemEntityCreateRequestDto.setUserId(id);
    return ResponseEntity.ok(
        cartServiceInterface
            .addItemEntityToCart(cartItemEntityCreateRequestDto)
            .map(cartItemEntityIndexMapper::toDto));
  }

  @PutMapping("/{cartId}/cartItems/{cartItemId}")
  public ResponseEntity<Page<CartItemEntityIndexDto>> updateCartItemQuantity(
      @PathVariable("cartId") int cartId,
      @PathVariable("cartItemId") int cartItemId,
      @RequestBody CartItemEntityUpdateQuantityDto cartItemEntityUpdateQuantityDto) {
    cartItemEntityUpdateQuantityDto.setId(cartItemId);
    return ResponseEntity.ok(
        cartServiceInterface
            .updateCartItemQuantity(cartItemEntityUpdateQuantityDto)
            .map(cartItemEntityIndexMapper::toDto));
  }

  @DeleteMapping("/{cartId}/cartItems/{cartItemId}")
  public ResponseEntity<Void> delete(
      @PathVariable("cartId") int cartId, @PathVariable("cartItemId") int cartItemId) {
    cartServiceInterface.delete(cartItemId);
    return ResponseEntity.ok().build();
  }
}
