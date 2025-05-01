package com.mycom.myapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertOrderResponse {
    private String message;
    private int orderId;
    private String status;
    private int orderItemCount;
}
