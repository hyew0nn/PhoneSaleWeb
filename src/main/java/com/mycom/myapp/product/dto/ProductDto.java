package com.mycom.myapp.product.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private String brandName;
    private String color;
    private int price;
    private LocalDate releaseDate;
    private String storage;
    private String specs;
    private int stock;
}
