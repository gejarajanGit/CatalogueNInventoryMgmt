package com.microcredentials.pss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductCatalogue {

    private Long id;
    private String name;
    private String techSpec;
    private BigDecimal price;
    private String category;
    private String subcategory;
    private String brand;
    private String color;
    private int height;
    private int width;
    private int depth;
    private int diagonalSize;
    private String manufactureYear;
}