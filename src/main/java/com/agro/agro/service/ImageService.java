package com.agro.agro.service;


import com.agro.agro.dto.ImageDto;
import com.agro.agro.exceptions.ProductNotFoundException;
import com.agro.agro.mapper.ImageMapper;
import com.agro.agro.model.Image;
import com.agro.agro.model.Product;
import com.agro.agro.repository.ImageRepository;
import com.agro.agro.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ImageService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public void save(List<ImageDto> imageDto) {
        if (!imageDto.isEmpty()) {
            Product product = productRepository.findById(imageDto.get(0).getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(imageDto.get(0).getProductId().toString()));
            for (ImageDto request: imageDto) {
                Image image = imageMapper.map(request,product);
                imageRepository.save(image);
            }
        }
    }

    public List<ImageDto> getAllImagesForProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId.toString()));
        return imageRepository.findByProduct(product)
                .stream()
                .map(imageMapper::mapToDto)
                .collect(toList());
    }
}
