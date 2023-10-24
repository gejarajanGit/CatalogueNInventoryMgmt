package com.microcredentials.cs.controller;

import com.microcredentials.cs.error.ProductCatalogueNotFoundException;
import com.microcredentials.cs.model.ProductCatalogue;
import com.microcredentials.cs.service.CatalogueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatalogueController.class)
class CatalogueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CatalogueService catalogueService;

    ProductCatalogue productCatalogue;

    @BeforeEach
    void setUp() {
        productCatalogue = ProductCatalogue.builder()
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
    }

    @Test
    @Disabled
    void getProductOfSubcategory() throws Exception {
        List<ProductCatalogue> productCatalogueList = new ArrayList<>();
        productCatalogueList.add(productCatalogue);
        Mockito.when(catalogueService.getProductOfSubcategory("4KTV"))
                .thenReturn(productCatalogueList);

        mockMvc.perform(get("/subcategory/4KTV")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.color")
                                            .value(productCatalogue.getColor()));
    }

    @Test
    @Disabled
    void addProduct() throws Exception {
        ProductCatalogue inputProductCatalogue = ProductCatalogue.builder()
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
        Mockito.when(catalogueService.addProductCatalogue(inputProductCatalogue))
                .thenReturn(productCatalogue);

        mockMvc.perform(post("/addProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"id\": 1,\n" +
                                "    \"name\": \"TV\",\n" +
                                "    \"techSpec\": \"4k TV\",\n" +
                                "    \"price\": 50000.00,\n" +
                                "    \"category\": \"Television\",\n" +
                                "    \"subcategory\": \"4KTV\",\n" +
                                "    \"brand\": \"Sony\",\n" +
                                "    \"color\": \"Black\",\n" +
                                "    \"height\": 10,\n" +
                                "    \"width\": 30,\n" +
                                "    \"depth\": 5,\n" +
                                "    \"diagonalSize\": 55,\n" +
                                "    \"manufactureYear\": \"2023\"\n" +
                                "}"))
                        .andExpect(status().isOk());
    }
}