package com.mycom.myapp.user.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class InsertUserResponse {
    private int userId;
    private String message;
}
