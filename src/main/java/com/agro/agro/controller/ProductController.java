package com.agro.agro.controller;

import com.agro.agro.dto.ProductRequest;
import com.agro.agro.dto.ProductResponse;
import com.agro.agro.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

   @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest) {
       productService.save(productRequest);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return status(HttpStatus.OK).body(productService.getProduct(id));
    }

    @GetMapping(params = "username")
    public ResponseEntity<List<ProductResponse>> getProductsByUsername(@RequestParam String username) {
        return status(HttpStatus.OK).body(productService.getProductsByUsername(username));
    }
}
