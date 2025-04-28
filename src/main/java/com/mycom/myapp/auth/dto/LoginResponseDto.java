package com.mycom.myapp.auth.dto;

import com.mycom.myapp.user.dto.UserDto;
import lombok.Data;

@Data
public class LoginResponseDto {
    private String result;
    private UserSessionDto userSessionDto;
}
