package com.microcredentials.cs.repository;

import com.microcredentials.cs.model.ProductCatalogue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CatalogueRepositoryTest {

    @Autowired
    CatalogueRepository catalogueRepository;
    @Autowired
    TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        ProductCatalogue productCatalogue = ProductCatalogue.builder()
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
                .techSpec("4k TV").build();
        entityManager.persist(productCatalogue);
    }

    @Test
    @DisplayName("Find By Brand and color of product")
    @Disabled
    void withValidBrandAndColorReturnAllProductCatalogue() {
        Optional<ProductCatalogue> productCatalogue = catalogueRepository.findByBrandAndColor("Sony", "Black");
        assertEquals("Sony", productCatalogue.get().getBrand());
        assertEquals("Black", productCatalogue.get().getColor());
    }

    @Test
    @Disabled
    void findByCategory() {
        List<ProductCatalogue> productCatalogue = catalogueRepository.findByCategory("Television");
        assertEquals("Television", productCatalogue.get(0).getCategory());
    }

    @Test
    @Disabled
    void findBySubcategory() {
        List<ProductCatalogue> productCatalogue = catalogueRepository.findBySubcategory("4KTV");
        assertEquals("4KTV", productCatalogue.get(0).getSubcategory());
    }
}