package com.agro.agro.mapper;
import com.agro.agro.dto.CommentDto;
import com.agro.agro.model.Comment;
import com.agro.agro.model.Product;
import com.agro.agro.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "commentId", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "user", source = "user")
    Comment map(CommentDto commentDto, Product product, User user);

    @Mapping(target = "productId", expression = "java(comment.getProduct().getProductId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentDto mapToDto(Comment comment);

}
