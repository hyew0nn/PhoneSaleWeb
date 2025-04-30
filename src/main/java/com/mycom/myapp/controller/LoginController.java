package com.mycom.myapp.controller;

import com.mycom.myapp.service.LoginService;
import com.mycom.myapp.dto.GetUserResponse;
import com.mycom.myapp.dto.GetUserRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<GetUserResponse> login(
            @RequestBody GetUserRequest getUserRequest,
            HttpSession session
    ){
        GetUserResponse authResponseDto = loginService.login(getUserRequest);
        session.setAttribute("userId", authResponseDto.getId());
        return ResponseEntity.ok(authResponseDto);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("logout success");
    }
}
