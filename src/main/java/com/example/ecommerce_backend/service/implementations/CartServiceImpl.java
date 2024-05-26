package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.exception.InvalidQuantityException;
import com.example.ecommerce_backend.exception.InvalidStateException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.model.dto.cartitementity.CartItemEntityCreateRequestDto;
import com.example.ecommerce_backend.model.dto.cartitementity.CartItemEntityUpdateQuantityDto;
import com.example.ecommerce_backend.model.entity.CartItemEntity;
import com.example.ecommerce_backend.model.entity.ItemEntity;
import com.example.ecommerce_backend.model.entity.UserEntity;
import com.example.ecommerce_backend.repository.CartItemEntityRepository;
import com.example.ecommerce_backend.repository.ProductEntityRepository;
import com.example.ecommerce_backend.service.interfaces.CartServiceInterface;
import com.example.ecommerce_backend.service.interfaces.ItemServiceInterface;
import com.example.ecommerce_backend.service.interfaces.UserServiceInterface;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartServiceInterface {

  private final CartItemEntityRepository cartItemEntityRepository;

  private final ItemServiceInterface itemServiceInterface;

  private final UserServiceInterface userServiceInterface;

  private final ProductEntityRepository productEntityRepository;

  public Page<CartItemEntity> getAllItemEntitiesFromCart(int cartId, Pageable pageable) {
    return cartItemEntityRepository.findByUser_Id(cartId, pageable);
  }

  public Page<CartItemEntity> addItemEntityToCart(
      @NotNull CartItemEntityCreateRequestDto cartItemEntityCreateRequestDto) {
    ItemEntity itemEntity;
    if (cartItemEntityCreateRequestDto.getItemEntityId() == null) {
      itemEntity =
          itemServiceInterface.findItemEntityUsingVariationEntityIdSet(
              cartItemEntityCreateRequestDto.getProductId(),
              cartItemEntityCreateRequestDto.getVariationEntityIdSet());
    } else {
      itemEntity =
          itemServiceInterface.findItemEntityById(cartItemEntityCreateRequestDto.getItemEntityId());
    }

    UserEntity userEntity =
        userServiceInterface.findUserById(cartItemEntityCreateRequestDto.getUserId());

    if (itemEntity.isDisabled() || itemEntity.getSku() == null || itemEntity.getPrice() == null) {
      throw new InvalidStateException("The requested item is disabled");
    } else if (Integer.parseInt(itemEntity.getStock())
        < cartItemEntityCreateRequestDto.getQuantity()) {
      throw new InvalidQuantityException(
          "The requested item stock is smaller than the requested quantity");
    }

    CartItemEntity newCartItemEntity =
        CartItemEntity.builder()
            .sku(itemEntity.getSku())
            .user(userEntity)
            .itemEntity(itemEntity)
            .quantity(cartItemEntityCreateRequestDto.getQuantity())
            .build();

    cartItemEntityRepository.save(newCartItemEntity);
    return cartItemEntityRepository.findByUser_Id(
        userEntity.getId(), PageRequest.of(0, 10, Sort.Direction.DESC, "id"));
  }

  @Override
  public Page<CartItemEntity> updateCartItemQuantity(
      @NotNull CartItemEntityUpdateQuantityDto cartItemEntityUpdateQuantityDto) {
    Optional<CartItemEntity> cartItemEntity =
        cartItemEntityRepository.findById(cartItemEntityUpdateQuantityDto.getId());

    if (cartItemEntity.isEmpty()) {
      throw new ResourceNotFoundException(
          "Could not find cart item with id " + cartItemEntityUpdateQuantityDto.getId());
    } else if (Integer.parseInt(cartItemEntity.get().getItemEntity().getStock())
        < cartItemEntityUpdateQuantityDto.getQuantity()) {
      throw new InvalidQuantityException(
          "The requested item stock is smaller than the requested quantity");
    }

    cartItemEntity.get().setQuantity(cartItemEntityUpdateQuantityDto.getQuantity());
    cartItemEntityRepository.save(cartItemEntity.get());
    return cartItemEntityRepository.findByUser_Id(
        cartItemEntity.get().getUser().getId(), PageRequest.of(0, 10, Sort.Direction.DESC, "id"));
  }

  @Override
  public void delete(int id) {
    if (!cartItemEntityRepository.existsById(id)) {
      throw new ResourceNotFoundException("Could not find cart item with id " + id);
    }

    cartItemEntityRepository.deleteById(id);
  }
}
