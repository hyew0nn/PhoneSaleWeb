package com.mycom.myapp.user.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
