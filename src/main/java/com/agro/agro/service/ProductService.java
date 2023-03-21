package com.agro.agro.service;

import com.agro.agro.dto.ProductRequest;
import com.agro.agro.dto.ProductResponse;
import com.agro.agro.exceptions.ProductNotFoundException;
import com.agro.agro.mapper.ProductMapper;
import com.agro.agro.model.Product;
import com.agro.agro.model.User;
import com.agro.agro.repository.ProductRepository;
import com.agro.agro.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {

    private final AuthService authService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    public Product save(ProductRequest productRequest) {
        User currentUser = authService.getCurrentUser();
        return productRepository.save(productMapper.map(productRequest,currentUser));
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
        return productMapper.mapToDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return productRepository.findByUser(user)
                .stream()
                .map(productMapper::mapToDto)
                .collect(toList());
    }

}
