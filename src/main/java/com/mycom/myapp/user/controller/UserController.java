package com.mycom.myapp.user.controller;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.InsertUserResponse;
import com.mycom.myapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public InsertUserResponse insertUser(UserDto userDto){
        return userService.insertUser(userDto);
    }
}
