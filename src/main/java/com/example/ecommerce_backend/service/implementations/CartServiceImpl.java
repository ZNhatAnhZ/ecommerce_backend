package com.example.ecommerce_backend.service.implementations;

import com.example.ecommerce_backend.dto.CartItemEntity.CartItemEntityCreateRequestDto;
import com.example.ecommerce_backend.dto.CartItemEntity.CartItemEntityIndexDto;
import com.example.ecommerce_backend.dto.CartItemEntity.CartItemEntityUpdateQuantityDto;
import com.example.ecommerce_backend.exception.InvalidQuantityException;
import com.example.ecommerce_backend.exception.InvalidStateException;
import com.example.ecommerce_backend.exception.ResourceNotFoundException;
import com.example.ecommerce_backend.mapper.CartItemEntity.CartItemEntityIndexMapper;
import com.example.ecommerce_backend.model.CartItemEntity;
import com.example.ecommerce_backend.model.ItemEntity;
import com.example.ecommerce_backend.model.UserEntity;
import com.example.ecommerce_backend.repository.CartItemEntityRepository;
import com.example.ecommerce_backend.repository.ProductEntityRepository;
import com.example.ecommerce_backend.service.interfaces.CartServiceInterface;
import com.example.ecommerce_backend.service.interfaces.ItemServiceInterface;
import com.example.ecommerce_backend.service.interfaces.UserServiceInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartServiceInterface {
    private final CartItemEntityRepository cartItemEntityRepository;
    private final CartItemEntityIndexMapper cartItemEntityIndexMapper;
    private final ItemServiceInterface itemServiceInterface;
    private final UserServiceInterface userServiceInterface;
    private final ProductEntityRepository productEntityRepository;

    public Page<CartItemEntityIndexDto> getAllItemEntitiesFromCart(int cartId, Pageable pageable) {
        return cartItemEntityRepository.findByUser_Id(cartId, pageable).map(cartItemEntityIndexMapper::toDto);
    }

    public CartItemEntityIndexDto addItemEntityToCart(CartItemEntityCreateRequestDto cartItemEntityCreateRequestDto) {
        ItemEntity itemEntity = itemServiceInterface.findItemEntityById(cartItemEntityCreateRequestDto.getItemEntityId());
        UserEntity userEntity = userServiceInterface.findUserById(cartItemEntityCreateRequestDto.getUserId());

        if (itemEntity.isDisabled() || itemEntity.getSku() == null || itemEntity.getPrice() == null) {
            throw new InvalidStateException("The requested item is disabled");
        } else if (Integer.parseInt(itemEntity.getStock()) < cartItemEntityCreateRequestDto.getQuantity()) {
            throw new InvalidQuantityException("The requested item stock is smaller than the requested quantity");
        }

        CartItemEntity newCartItemEntity = CartItemEntity.builder()
                .sku(itemEntity.getSku())
                .user(userEntity)
                .itemEntity(itemEntity)
                .quantity(cartItemEntityCreateRequestDto.getQuantity())
                .build();

        return cartItemEntityIndexMapper.toDto(cartItemEntityRepository.save(newCartItemEntity));
    }

    @Override
    public CartItemEntityIndexDto updateCartItemQuantity(CartItemEntityUpdateQuantityDto cartItemEntityUpdateQuantityDto) {
        Optional<CartItemEntity> cartItemEntity = cartItemEntityRepository.findById(cartItemEntityUpdateQuantityDto.getId());

        if (cartItemEntity.isEmpty()) {
            throw new ResourceNotFoundException("Could not find cart item with id " + cartItemEntityUpdateQuantityDto.getId());
        } else if (Integer.parseInt(cartItemEntity.get().getItemEntity().getStock()) < cartItemEntityUpdateQuantityDto.getQuantity()) {
            throw new InvalidQuantityException("The requested item stock is smaller than the requested quantity");
        }

        cartItemEntity.get().setQuantity(cartItemEntityUpdateQuantityDto.getQuantity());
        return cartItemEntityIndexMapper.toDto(cartItemEntityRepository.save(cartItemEntity.get()));
    }

    @Override
    public void delete(int id) {
        if (!cartItemEntityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Could not find cart item with id " + id);
        }

        cartItemEntityRepository.deleteById(id);
    }
}
