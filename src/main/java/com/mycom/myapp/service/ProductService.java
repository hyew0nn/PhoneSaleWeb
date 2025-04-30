package com.mycom.myapp.service;

import com.mycom.myapp.dto.GetProductsResponse;
import com.mycom.myapp.dto.ProductDto;

public interface ProductService {
    GetProductsResponse findProductAll();
    ProductDto findProductById(int productId);
}
