package com.agro.agro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userId;
    private String username;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String duration;
}
