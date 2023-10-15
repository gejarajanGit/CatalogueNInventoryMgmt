package com.microcredentials.cs.controller;

import com.microcredentials.cs.model.ProductCatalogue;
import com.microcredentials.cs.repository.CatalogueRepository;
import com.microcredentials.cs.service.CatalogueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogue")
@RequiredArgsConstructor
public class CatalogueController {

    @Autowired
    CatalogueService catalogueService;

    @GetMapping("/allProducts")
    public List<ProductCatalogue> getCatalogue(){
        return catalogueService.getCatalogue();
    }

    @GetMapping("/category/{category}")
    public List<ProductCatalogue> getProductsByCategory(@PathVariable String category){
        return catalogueService.getProductsByCategory(category);
    }

    @GetMapping("/subcategory/{subcategory}")
    public List<ProductCatalogue> getProductOfSubcategory(@PathVariable String subcategory){
        return catalogueService.getProductOfSubcategory(subcategory);
    }

    @GetMapping("/product")
    public ProductCatalogue getProductByBrandAndColor(String brand, String color){
        return catalogueService.getProduct(brand, color);
    }

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody ProductCatalogue productCatalogue){
        catalogueService.addProductCatalogue(productCatalogue);
    }
}