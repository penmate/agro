package com.agro.agro.mapper;

import com.agro.agro.dto.ProductRequest;
import com.agro.agro.dto.ProductResponse;
import com.agro.agro.model.Product;
import com.agro.agro.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "productRequest.description")
    @Mapping(target = "productName", source = "productRequest.name")
    Product map(ProductRequest productRequest, User user);

    @Mapping(target = "id", source = "productId")
    @Mapping(target = "userName", source = "user.username")
    ProductResponse mapToDto(Product product);
}
