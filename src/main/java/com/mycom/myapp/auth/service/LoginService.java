package com.mycom.myapp.auth.service;

import com.mycom.myapp.user.dto.LoginResponseDto;
import com.mycom.myapp.user.dto.LoginRequestDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
