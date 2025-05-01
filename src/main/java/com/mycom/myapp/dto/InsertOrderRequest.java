package com.mycom.myapp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InsertOrderRequest {
    private int customerId;
    private BigDecimal totalPrice;
    private String recipientName;
    private String recipientPhone;
    private String shippingAddress;
    private List<InsertOrderItemRequest> orderItems;
}
