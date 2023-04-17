package com.agro.agro.controller;

import com.agro.agro.dto.CommentDto;
import com.agro.agro.dto.ImageDto;
import com.agro.agro.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/images/")
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/by_product/{productId}")
    public ResponseEntity<List<ImageDto>> getAllImageForProduct(@PathVariable Long productId) {
        return ResponseEntity.status(OK).body(imageService.getAllImagesForProduct(productId));
    }

}
