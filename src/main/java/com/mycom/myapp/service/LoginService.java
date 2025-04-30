package com.mycom.myapp.service;

import com.mycom.myapp.dto.GetUserResponse;
import com.mycom.myapp.dto.GetUserRequest;

public interface LoginService {
    GetUserResponse login(GetUserRequest getUserRequest);
}
