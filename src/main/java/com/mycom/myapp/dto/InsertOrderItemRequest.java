package com.mycom.myapp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InsertOrderItemRequest {
    private int productId;
    private int quantity;
    private BigDecimal price;
}
