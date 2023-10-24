package com.microcredentials.cs.service;

import com.microcredentials.cs.error.ProductCatalogueNotFoundException;
import com.microcredentials.cs.model.ProductCatalogue;
import com.microcredentials.cs.repository.CatalogueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CatalogueServiceTest {

    @Autowired
    CatalogueService catalogueService;

    @Autowired
    CatalogueRepository catalogueRepository;

    @BeforeEach
    void setUp() {
        List<ProductCatalogue> productCatalogueList = new ArrayList<ProductCatalogue>();
        productCatalogueList.add(ProductCatalogue.builder()
                                                    .id(1L)
                                                    .manufactureYear("2023")
                                                    .depth(5)
                                                    .name("TV")
                                                    .category("Television")
                                                    .subcategory("4KTV")
                                                    .height(10)
                                                    .width(30)
                                                    .diagonalSize(55)
                                                    .price(BigDecimal.valueOf(50000.00))
                                                    .color("Black")
                                                    .brand("Sony")
                                                    .techSpec("4k TV").build());

        Mockito.when(catalogueRepository.findAll())
                .thenReturn(productCatalogueList);
    }

    @Test
    @Disabled
    void fetchAllCatalogues() throws ProductCatalogueNotFoundException {
        String brand = "Sony";
        String color = "Black";
        List<ProductCatalogue> productCatalogue = catalogueService.getCatalogue();
        assertEquals(brand, productCatalogue.get(0).getBrand());
        assertEquals(color, productCatalogue.get(0).getColor());
    }

    @Test
    @Disabled
    void withValidCategoryThenReturnAllCataloguesByCategory() throws ProductCatalogueNotFoundException {
        String category = "Television";
        List<ProductCatalogue> productCatalogue = catalogueService.getProductsByCategory(category);
        assertEquals(category, productCatalogue.get(0).getBrand());
    }

    @Test
    @Disabled
    void withValidSubCategoryThenReturnAllCataloguesBySubCategory() throws ProductCatalogueNotFoundException {
        String subCategory = "Television";
        List<ProductCatalogue> productCatalogue = catalogueService.getProductOfSubcategory(subCategory);
        assertEquals(subCategory, productCatalogue.get(0).getBrand());
    }

    @Test
    @Disabled
    void withValidBrandAndColorThenReturnAllCatalogues() {
        String brand = "Sony";
        String color = "Black";
        Optional<ProductCatalogue> productCatalogue = catalogueRepository.findByBrandAndColor(brand, color);
        assertEquals(brand, productCatalogue.get().getBrand());
        assertEquals(color, productCatalogue.get().getColor());
    }
}