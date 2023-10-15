package com.microcredentials.pss.controller;

import com.microcredentials.pss.model.Inventory;
import com.microcredentials.pss.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class ProductSearchController {

    @Autowired
    ProductSearchService productSearchService;

    @GetMapping("/inHandProducts")
    public List<Inventory> getInHandProduct(){
        return productSearchService.getProductsInHand();
    }

    @GetMapping("/allProducts")
    public List<Inventory> getProduct(){
        return productSearchService.getProducts();
    }

    @GetMapping("/product/{productId}")
    public Inventory getProductbyId(@PathVariable int productId){
        return productSearchService.getProductById(productId);
    }
}
