package com.microcredentials.cs.service;

import com.microcredentials.cs.model.ProductCatalogue;
import com.microcredentials.cs.repository.CatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogueService {

    @Autowired
    CatalogueRepository catalogueRepository;

    public List<ProductCatalogue> getCatalogue(){
        return catalogueRepository.findAll();
    }

    public List<ProductCatalogue> getProductsByCategory(String category){
        return catalogueRepository.findByCategory(category);
    }

    public List<ProductCatalogue> getProductOfSubcategory(String subcategory){
        return catalogueRepository.findBySubcategory(subcategory);
    }

    public ProductCatalogue getProduct(String brand, String color) {
        return catalogueRepository.findByBrandAndColor(brand, color);
    }

    public void addProductCatalogue(ProductCatalogue productCatalogue) {
        catalogueRepository.save(productCatalogue);
    }
}
