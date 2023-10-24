package com.microcredentials.cs.service;

import com.microcredentials.cs.error.ProductCatalogueNotFoundException;
import com.microcredentials.cs.model.ProductCatalogue;
import com.microcredentials.cs.repository.CatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogueService {

    @Autowired
    CatalogueRepository catalogueRepository;

    public List<ProductCatalogue> getCatalogue(){
        return catalogueRepository.findAll();
    }

    public List<ProductCatalogue> getProductsByCategory(String category) throws ProductCatalogueNotFoundException {
        List<ProductCatalogue> productCatalogueList = catalogueRepository.findByCategory(category);
        if(productCatalogueList.size()==0)
            throw new ProductCatalogueNotFoundException("No product catalogues found");
        return productCatalogueList;
    }

    public List<ProductCatalogue> getProductOfSubcategory(String subcategory) throws ProductCatalogueNotFoundException {
        List<ProductCatalogue> productCatalogueList =  catalogueRepository.findBySubcategory(subcategory);
        if(productCatalogueList.size()==0)
            throw new ProductCatalogueNotFoundException("No product catalogues found");
        return productCatalogueList;
    }

    public ProductCatalogue getProduct(String brand, String color) throws ProductCatalogueNotFoundException {
        Optional<ProductCatalogue> productCatalogue = catalogueRepository.findByBrandAndColor(brand, color);
        if(!productCatalogue.isPresent())
            throw new ProductCatalogueNotFoundException("Product catalogue not found exception");
        return productCatalogue.get();
    }

    public ProductCatalogue addProductCatalogue(ProductCatalogue productCatalogue) {
        return catalogueRepository.save(productCatalogue);
    }
}
