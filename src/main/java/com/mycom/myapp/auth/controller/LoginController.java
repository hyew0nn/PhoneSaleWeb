package com.mycom.myapp.auth.controller;

import com.mycom.myapp.auth.service.LoginService;
import com.mycom.myapp.user.dto.LoginResponseDto;
import com.mycom.myapp.user.dto.LoginRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpSession session
    ){
        LoginResponseDto authResponseDto = loginService.login(loginRequestDto);
        if(authResponseDto.getResult().equals("success")){
            session.setAttribute("userDto", authResponseDto.getUserDto());
        }
        return ResponseEntity.ok(authResponseDto);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("logout success");
    }
}
