package com.mycom.myapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String email;
    private String password;
    private String name;
    private String address;
    private String phone;
    private int adminRole;
}
