package com.agro.agro.repository;

import com.agro.agro.model.Product;
import com.agro.agro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    void deleteProductByProductId(Long id);
    List<Product> findByUser(User user);

    List<Product> findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String key1, String key2);
}
