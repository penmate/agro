package com.agro.agro.controller;

import com.agro.agro.dto.ImageDto;
import com.agro.agro.dto.ProductRequest;
import com.agro.agro.dto.ProductResponse;
import com.agro.agro.model.Product;
import com.agro.agro.service.ImageService;
import com.agro.agro.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ImageService imageService;

   @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> createProduct(@RequestPart("productRequest") ProductRequest productRequest,
                                                @RequestPart("imageRequest")MultipartFile[] images) {
       try {
           Product product = productService.save(productRequest);
           List<ImageDto> imageDtos = uploadImage(images, product.getProductId());
           imageService.save(imageDtos);
           return new ResponseEntity<>(HttpStatus.CREATED);
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return null;
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(@RequestParam(defaultValue = "") String searchKey) {
        return status(HttpStatus.OK).body(productService.getAllProducts(searchKey));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return status(HttpStatus.OK).body(productService.getProduct(id));
    }

    @GetMapping("/by_user/{username}")
    public ResponseEntity<List<ProductResponse>> getProductsByUsername(@PathVariable String username) {
        return status(HttpStatus.OK).body(productService.getProductsByUsername(username));
    }

    public List<ImageDto> uploadImage(MultipartFile[] multipartFiles, Long productId) throws IOException {
        List<ImageDto> images = new ArrayList<>();
        for (MultipartFile file: multipartFiles) {
            ImageDto image = new ImageDto(
                    file.getOriginalFilename(),
                    productId,
                    file.getContentType(),
                    file.getBytes()
            );
            images.add(image);
        }
        return images;
    }
}
