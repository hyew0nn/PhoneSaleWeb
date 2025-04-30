package com.mycom.myapp.service;

import com.mycom.myapp.dto.GetUserRequest;
import com.mycom.myapp.dto.GetUserResponse;
import com.mycom.myapp.entity.User;
import com.mycom.myapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final UserRepository userRepository;

    @Override
    public GetUserResponse login(GetUserRequest getUserRequest) {
        Optional<User> optionalUser = userRepository.findUserByEmail(getUserRequest.getEmail());

        if (optionalUser.isEmpty()) {
            // 사용자를 찾을 수 없는 경우 예외 발생
//            throw new IllegalAccessException("User not found with email: " + getUserRequest.getEmail());
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(getUserRequest.getPassword())) {
            // 비밀번호 불일치 시 예외 발생
//            throw new IllegalAccessException("Invalid password");
        }

        // 인증 성공 시 사용자 정보만 반환
        return GetUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
