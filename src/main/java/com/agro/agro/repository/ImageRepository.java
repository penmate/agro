package com.agro.agro.repository;

import com.agro.agro.model.Image;
import com.agro.agro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProduct(Product product);
}
