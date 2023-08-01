//package com.example.ecommerce_backend.service.implementations;
//
//import com.example.ecommerce_backend.dto.ProductEntity.ProductEntityCreateDto;
//import com.example.ecommerce_backend.model.ProductEntity;
//import com.example.ecommerce_backend.repository.ProductEntityRepository;
//import com.example.ecommerce_backend.service.interfaces.ProductServiceInterface;
//import jakarta.transaction.Transactional;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Getter
//@Setter
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Transactional
//public class ProductServiceImpl implements ProductServiceInterface {
//    private final ProductEntityRepository productEntityRepository;
//
//    public List<ProductEntity> getAllProducts() {
//        return productEntityRepository.findAll();
//    }
//
//    public Optional<ProductEntity> getProductById(Integer id) {
//        return productEntityRepository.findById(id);
//    }
//
//    public ProductEntity createProduct(ProductEntityCreateDto productEntityCreateDto) {
//
//        return productRepository.save(product);
//    }
//
//    public Product updateProduct(Product product) {
//        return productRepository.save(product);
//    }
//
//    public void deleteProduct(int id) {
//        productEntityRepository.deleteById(id);
//    }
//}
