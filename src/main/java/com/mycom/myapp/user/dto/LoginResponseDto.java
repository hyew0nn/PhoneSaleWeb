package com.mycom.myapp.user.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String result;
    private UserDto userDto;
}
