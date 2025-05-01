package com.mycom.myapp.service;

import com.mycom.myapp.dto.GetProductsResponse;
import com.mycom.myapp.dto.InsertOrderItemRequest;
import com.mycom.myapp.dto.ProductDto;

import java.util.List;

public interface ProductService {
    GetProductsResponse findProductAll();
    ProductDto findProductById(int productId);
    Boolean decreaseProductStock(List<InsertOrderItemRequest> items);
}
