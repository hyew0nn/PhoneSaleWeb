package com.mycom.myapp.service;

import com.mycom.myapp.dto.InsertOrderItemRequest;
import com.mycom.myapp.dto.InsertOrderRequest;
import com.mycom.myapp.dto.InsertOrderResponse;
import com.mycom.myapp.dto.OrderDto;
import com.mycom.myapp.entity.*;
import com.mycom.myapp.exception.ProductException;
import com.mycom.myapp.exception.UserException;
import com.mycom.myapp.repository.OrderItemRepository;
import com.mycom.myapp.repository.OrderRepository;
import com.mycom.myapp.repository.ProductRepository;
import com.mycom.myapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    // insert order
    public InsertOrderResponse insertOrder(InsertOrderRequest insertOrderRequest) {

        Optional<User> user = userRepository.findById(insertOrderRequest.getCustomerId());

        if(user.isEmpty()) {
            throw new UserException.UserNotFoundException(insertOrderRequest.getCustomerId());
        }

        // 1. Orders 엔티티 생성
        Orders order = Orders.builder()
                .customerId(insertOrderRequest.getCustomerId())
                .totalPrice(insertOrderRequest.getTotalPrice())
                .orderDate(LocalDateTime.now())
                .recipientName(insertOrderRequest.getRecipientName())
                .recipientPhone(insertOrderRequest.getRecipientPhone())
                .shippingAddress(insertOrderRequest.getShippingAddress())
                .orderStatus(OrderType.결제완료) // 초기 상태 설정
                .build();

        // 2. Orders 저장
        Orders savedOrder = orderRepository.save(order);

        List<OrderItems> orderItemsList = new ArrayList<>();

        for (InsertOrderItemRequest itemRequest : insertOrderRequest.getOrderItems()) {
            // 3.1 상품 존재 여부 확인
            Optional<Product> product = productRepository.findById(itemRequest.getProductId());

            if (product.isEmpty()) {
                throw new ProductException.ProductNotFoundException(itemRequest.getProductId());
            }

            // 3.2 OrderItems 엔티티 생성
            OrderItems orderItem = OrderItems.builder()
                    .orderId(savedOrder.getId())
                    .product(product.get())
                    .quantity(itemRequest.getQuantity())
                    .price(itemRequest.getPrice())
                    .build();

            // 3.3 저장 및 응답 DTO 준비
            OrderItems savedOrderItem = orderItemRepository.save(orderItem);
            orderItemsList.add(savedOrderItem);
        }

        productService.decreaseProductStock(insertOrderRequest.getOrderItems());

        savedOrder.setOrderItems(orderItemsList);

        return InsertOrderResponse.builder()
                .orderId(savedOrder.getId())
                .message("주문이 성공적으로 생성되었습니다.")
                .orderItemCount(orderItemsList.size())
                .status("결제완료")
                .build();
    }
}
