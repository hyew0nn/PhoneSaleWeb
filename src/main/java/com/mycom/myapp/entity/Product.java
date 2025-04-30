package com.mycom.myapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    @Column(name = "model_name")
    private String name;
    private String color;
    private int price;
    private LocalDate releaseDate;
    private String storage;
    private String specs;
    @Column(name = "sales_count")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
