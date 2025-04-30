package com.mycom.myapp.service;

import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.dto.InsertUserResponse;

public interface UserService {
    InsertUserResponse insertUser(UserDto userDto);
}
