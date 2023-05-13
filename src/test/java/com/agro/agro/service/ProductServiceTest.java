package com.agro.agro.service;

import com.agro.agro.dto.ProductResponse;
import com.agro.agro.mapper.ProductMapper;
import com.agro.agro.model.AmountType;
import com.agro.agro.model.Availability;
import com.agro.agro.model.Product;
import com.agro.agro.model.ProductCondition;
import com.agro.agro.repository.ProductRepository;
import com.agro.agro.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthService authService;
    @Mock
    private ProductMapper productMapper;

    private ProductService productService;

    @BeforeEach
    public void setup() {
        productService = new ProductService(authService, productRepository, productMapper, userRepository);
    }

    @Test
    @DisplayName("Should Find Product By Id")
    void shouldFindProductById() {

        Product product = new Product(123L, "First Product", "Ez egy product", 1000L, 900L, "Szeged", ProductCondition.NEW, 3L, AmountType.AMOUNT, Availability.AVAILABLE, Instant.now(), null);
        ProductResponse expectedProductResponse = new ProductResponse(123L, "First Product", "Ez egy product", 1000L, 900L, "Szeged", ProductCondition.NEW, 3L, AmountType.AMOUNT, Availability.AVAILABLE,"Test User" ,0 , "1 Hour ago");

        Mockito.when(productRepository.findById(123L)).thenReturn(Optional.of(product));
        Mockito.when(productMapper.mapToDto(Mockito.any(Product.class))).thenReturn(expectedProductResponse);

        ProductResponse actualProductResponse = productService.getProduct(123L);

        Assertions.assertThat(actualProductResponse.getId()).isEqualTo(expectedProductResponse.getId());
        Assertions.assertThat(actualProductResponse.getProductName()).isEqualTo(expectedProductResponse.getProductName());
    }
}