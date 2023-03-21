package com.agro.agro.repository;

import com.agro.agro.model.Product;
import com.agro.agro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByUser(User user);
}
