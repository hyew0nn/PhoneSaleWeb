package com.mycom.myapp.product.service;

import com.mycom.myapp.product.dto.GetProductsResponse;
import com.mycom.myapp.product.dto.ProductDto;
import com.mycom.myapp.product.entity.Product;
import com.mycom.myapp.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public GetProductsResponse findProductAll() {
        GetProductsResponse response = new GetProductsResponse();

        List<ProductDto> products = productRepository.getProducts();
        response.setProducts(products);
        response.setMessage("success");

        return response;
    }
}
