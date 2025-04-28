package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.InsertUserResponse;

public interface UserService {
    InsertUserResponse insertUser(UserDto userDto);
}
