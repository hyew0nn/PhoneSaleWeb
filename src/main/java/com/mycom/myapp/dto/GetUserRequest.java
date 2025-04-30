package com.mycom.myapp.dto;

import lombok.Data;

@Data
public class GetUserRequest {
    private String email;
    private String password;
}
