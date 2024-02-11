package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.cartitementity.CartItemEntityCreateRequestDto;
import com.example.ecommerce_backend.dto.cartitementity.CartItemEntityIndexDto;
import com.example.ecommerce_backend.dto.cartitementity.CartItemEntityUpdateQuantityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartServiceInterface {

  Page<CartItemEntityIndexDto> getAllItemEntitiesFromCart(int cartId, Pageable pageable);

  CartItemEntityIndexDto addItemEntityToCart(
      CartItemEntityCreateRequestDto cartItemEntityCreateRequestDto);

  CartItemEntityIndexDto updateCartItemQuantity(
      CartItemEntityUpdateQuantityDto cartItemEntityUpdateQuantityDto);

  void delete(int id);
}
