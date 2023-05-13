package com.agro.agro.mapper;

import com.agro.agro.dto.UserResponse;
import com.agro.agro.dto.UserUpdateRequest;
import com.agro.agro.model.Product;
import com.agro.agro.model.User;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "createdAt", source = "user.createdAt")
    @Mapping(target = "updatedAt", source = "user.updatedAt")
    @Mapping(target = "firstname", source = "userUpdateRequest.firstname")
    @Mapping(target = "lastname", source = "userUpdateRequest.lastname")
    @Mapping(target = "phone", source = "userUpdateRequest.phone")
    @Mapping(target = "email", source = "userUpdateRequest.email")
    public abstract  User map(UserUpdateRequest userUpdateRequest, User user);
    @Mapping(target = "duration", expression = "java(getDuration(user))")
    public abstract UserResponse mapToDto(User user);

    String getDuration(User user) {
        return TimeAgo.using(user.getUpdatedAt().toEpochMilli());
    }
}
