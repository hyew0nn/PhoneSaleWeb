package com.mycom.myapp.controller;

import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.dto.InsertUserResponse;
import com.mycom.myapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public InsertUserResponse insertUser(@RequestBody UserDto userDto){
        return userService.insertUser(userDto);
    }
}
