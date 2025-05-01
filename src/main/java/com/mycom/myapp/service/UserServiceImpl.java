package com.mycom.myapp.service;

import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.dto.InsertUserResponse;
import com.mycom.myapp.entity.User;
import com.mycom.myapp.exception.UserException;
import com.mycom.myapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                .address(userDto.getAddress())
                .adminRole(userDto.getAdminRole())
                .build();

        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            throw new UserException.UserExistException(user.getEmail());
        }

        User savedUser = userRepository.save(user);
        insertUserResponse.setMessage("success");
        insertUserResponse.setUserId(savedUser.getId());

        return insertUserResponse;
    }
}
