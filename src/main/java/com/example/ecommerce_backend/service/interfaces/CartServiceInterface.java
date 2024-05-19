package com.example.ecommerce_backend.service.interfaces;

import com.example.ecommerce_backend.dto.cartitementity.CartItemEntityCreateRequestDto;
import com.example.ecommerce_backend.dto.cartitementity.CartItemEntityUpdateQuantityDto;
import com.example.ecommerce_backend.model.CartItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartServiceInterface {

  Page<CartItemEntity> getAllItemEntitiesFromCart(int cartId, Pageable pageable);

  Page<CartItemEntity> addItemEntityToCart(
      CartItemEntityCreateRequestDto cartItemEntityCreateRequestDto);

  Page<CartItemEntity> updateCartItemQuantity(
      CartItemEntityUpdateQuantityDto cartItemEntityUpdateQuantityDto);

  void delete(int id);
}
