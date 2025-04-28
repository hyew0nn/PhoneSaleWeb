package com.mycom.myapp.auth.service;

import com.mycom.myapp.auth.dto.LoginRequestDto;
import com.mycom.myapp.auth.dto.UserSessionDto;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.auth.dto.LoginResponseDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final UserRepository userRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        LoginResponseDto authResponseDto = new LoginResponseDto();

        Optional<User> optionalUser = userRepository.findUserByEmail(loginRequestDto.getEmail());

        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            if(user.getPassword().equals(loginRequestDto.getPassword())){
                UserSessionDto userSessionDto = UserSessionDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .adminRole(user.getAdminRole())
                        .build();

                authResponseDto.setUserSessionDto(userSessionDto);
                authResponseDto.setResult("success");
            } else {
                authResponseDto.setResult("fail");
            }
        } else {
            authResponseDto.setResult("not found");
        }
        return authResponseDto;
    }
}
