package com.microcredentials.is.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    private Long id;
    private int productId;
    private int productName;
    private int quantity;
    private String productLocation;
}
