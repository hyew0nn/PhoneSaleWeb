package com.mycom.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetProductsResponse {
    public String message;
    public List<ProductDto> products;
}
