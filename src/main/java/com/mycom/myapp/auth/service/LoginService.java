package com.mycom.myapp.auth.service;

import com.mycom.myapp.auth.dto.LoginResponseDto;
import com.mycom.myapp.auth.dto.LoginRequestDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
