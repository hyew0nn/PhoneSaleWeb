package com.mycom.myapp.product.service;

import com.mycom.myapp.product.dto.GetProductsResponse;
import com.mycom.myapp.product.dto.ProductDto;
import com.mycom.myapp.product.entity.Product;

import java.util.List;

public interface ProductService {
    GetProductsResponse findProductAll();
}
