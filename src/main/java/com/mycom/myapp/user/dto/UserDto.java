package com.mycom.myapp.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private int id;
    private String email;
    private String name;
    private String address;
    private String phone;
    private String adminRole;
}
