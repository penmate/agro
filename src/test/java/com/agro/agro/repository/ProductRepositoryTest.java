package com.agro.agro.repository;

import com.agro.agro.BaseTest;
import com.agro.agro.model.AmountType;
import com.agro.agro.model.Availability;
import com.agro.agro.model.Product;
import com.agro.agro.model.ProductCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest extends BaseTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldSaveProduct() {
        Product expectedProductObject = new Product(null, "First Product", "Ez egy product", 1000L, 900L, "Szeged", ProductCondition.NEW, 3L, AmountType.AMOUNT, Availability.AVAILABLE, Instant.now(), null);

        Product actualProductObject = productRepository.save(expectedProductObject);
        assertThat(actualProductObject).usingRecursiveComparison()
                .ignoringFields("productId").isEqualTo(expectedProductObject);
    }
}