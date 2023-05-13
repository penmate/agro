package com.agro.agro.service;

import com.agro.agro.dto.UserResponse;

import com.agro.agro.dto.UserUpdateRequest;
import com.agro.agro.exceptions.SpringAgroException;
import com.agro.agro.mapper.UserMapper;
import com.agro.agro.model.User;
import com.agro.agro.repository.UserRepository;
import lombok.AllArgsConstructor;
import com.agro.agro.exceptions.ProductNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserResponse getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringAgroException("User not Found with Name" + username));
        return userMapper.mapToDto(user);
    }

    public void update(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findByUsername(userUpdateRequest.getUsername()).orElseThrow(() -> new SpringAgroException("User not Found with Name" + userUpdateRequest.getUsername()));
        userRepository.save(userMapper.map(userUpdateRequest, user));
    }
}
