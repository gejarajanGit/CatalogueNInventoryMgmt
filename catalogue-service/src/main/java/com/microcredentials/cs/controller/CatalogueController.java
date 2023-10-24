package com.microcredentials.cs.controller;

import com.microcredentials.cs.error.ProductCatalogueNotFoundException;
import com.microcredentials.cs.model.ProductCatalogue;
import com.microcredentials.cs.repository.CatalogueRepository;
import com.microcredentials.cs.service.CatalogueService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogue")
@RequiredArgsConstructor
public class CatalogueController {

    @Value("${server.port}")
    String port;

    @Autowired
    CatalogueService catalogueService;

    public static final Logger LOGGER = LoggerFactory.getLogger(CatalogueController.class);

    @GetMapping("/allProducts")
    public List<ProductCatalogue> getCatalogue() throws ProductCatalogueNotFoundException {
        LOGGER.info("Getting all the catalogues");
        return catalogueService.getCatalogue();
    }

    @GetMapping("/category/{category}")
    public List<ProductCatalogue> getProductsByCategory(@PathVariable String category) throws ProductCatalogueNotFoundException {
        LOGGER.info("Get all the products by category");
        return catalogueService.getProductsByCategory(category);
    }

    @GetMapping("/subcategory/{subcategory}")
    public List<ProductCatalogue> getProductOfSubcategory(@PathVariable String subcategory) throws ProductCatalogueNotFoundException {
        LOGGER.info("Get all the products by sub category");
        return catalogueService.getProductOfSubcategory(subcategory);
    }

    @GetMapping("/product")
    public ProductCatalogue getProductByBrandAndColor(String brand, String color) throws ProductCatalogueNotFoundException {
        System.out.println("Running in port : #" + port);
        LOGGER.info("Running in port : #" + port);
        return catalogueService.getProduct(brand, color);
    }

    @PostMapping("/addProduct")
    public ProductCatalogue addProduct(@RequestBody ProductCatalogue productCatalogue){
        LOGGER.info("");
        return catalogueService.addProductCatalogue(productCatalogue);
    }
}
