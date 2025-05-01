package com.mycom.myapp.dto;

import com.mycom.myapp.entity.OrderType;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private int customerId;
    private BigDecimal price;
    private String recipientName;
    private String recipientPhone;
    private String shippingAddress;
    private OrderType orderStatus;
}
