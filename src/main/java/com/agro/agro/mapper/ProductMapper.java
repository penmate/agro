package com.agro.agro.mapper;

import com.agro.agro.dto.ProductRequest;
import com.agro.agro.dto.ProductResponse;
import com.agro.agro.model.Product;
import com.agro.agro.model.User;
import com.agro.agro.repository.CommentRepository;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Autowired
    private CommentRepository commentRepository;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "productRequest.description")
    @Mapping(target = "productName", source = "productRequest.name")
    public abstract Product map(ProductRequest productRequest, User user);

    @Mapping(target = "id", source = "productId")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(product))")
    @Mapping(target = "duration", expression = "java(getDuration(product))")
    public abstract ProductResponse mapToDto(Product product);

    Integer commentCount(Product product) {
        return commentRepository.findByProduct(product).size();
    }

    String getDuration(Product product) {
        return TimeAgo.using(product.getCreatedDate().toEpochMilli());
    }
}
