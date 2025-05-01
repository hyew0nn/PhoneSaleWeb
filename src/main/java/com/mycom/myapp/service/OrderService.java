package com.mycom.myapp.service;

import com.mycom.myapp.dto.InsertOrderRequest;
import com.mycom.myapp.dto.InsertOrderResponse;

public interface OrderService {
    InsertOrderResponse insertOrder(InsertOrderRequest insertOrderRequest);
}
