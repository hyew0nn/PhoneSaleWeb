package com.mycom.myapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserResponse {
    private Integer id;
    private String name;
}
