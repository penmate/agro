package com.agro.agro.controller;

import com.agro.agro.dto.UserResponse;
import com.agro.agro.dto.UserUpdateRequest;
import com.agro.agro.model.User;
import com.agro.agro.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        return status(HttpStatus.OK).body(userService.getUser(username));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateEmployee(@RequestBody UserUpdateRequest userUpdateRequest) {
        userService.update(userUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
