package com.mycom.myapp.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSessionDto {
    private Integer id;
    private String email;
    private String name;
    private String adminRole;  // 권한 정보만 포함
}
