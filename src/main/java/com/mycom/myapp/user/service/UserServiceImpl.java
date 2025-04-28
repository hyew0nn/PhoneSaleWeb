package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.InsertUserResponse;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public InsertUserResponse insertUser(UserDto userDto) {
        InsertUserResponse insertUserResponse = new InsertUserResponse();
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .adminRole(userDto.getAdminRole())
                .build();
        try{
            userRepository.save(user);
            insertUserResponse.setMessage("success");
            insertUserResponse.setUserId(user.getId());
        }catch (Exception e){
            e.printStackTrace();
            insertUserResponse.setMessage("fail");
        }

        return insertUserResponse;
    }
}
