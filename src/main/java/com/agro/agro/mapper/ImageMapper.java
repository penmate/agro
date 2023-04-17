package com.agro.agro.mapper;


import com.agro.agro.dto.ImageDto;
import com.agro.agro.model.Image;
import com.agro.agro.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "imageId", ignore = true)
    @Mapping(target = "product", source = "product")
    Image map(ImageDto imageDto, Product product);

    @Mapping(target = "productId", expression = "java(image.getProduct().getProductId())")
    ImageDto mapToDto(Image image);
}
