package com.mycom.myapp.controller;

import com.mycom.myapp.dto.InsertOrderRequest;
import com.mycom.myapp.dto.InsertOrderResponse;
import com.mycom.myapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<InsertOrderResponse> createOrder(@RequestBody InsertOrderRequest insertOrderRequest) {
        InsertOrderResponse insertOrderResponse = orderService.insertOrder(insertOrderRequest);
        return ResponseEntity.ok(insertOrderResponse);
    }
}
