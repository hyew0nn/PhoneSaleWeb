package com.mycom.myapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int customerId;

    @Column(precision = 10, scale = 2) // decimal(10,2)
    private BigDecimal totalPrice;

    private LocalDateTime orderDate;
    private String recipientName;
    private String recipientPhone;
    private String shippingAddress;

    @Enumerated(EnumType.STRING)
    private OrderType orderStatus;

    @OneToMany(mappedBy = "orders")
    private List<OrderItems> orderItems;
}
