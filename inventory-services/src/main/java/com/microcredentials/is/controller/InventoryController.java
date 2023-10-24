package com.microcredentials.is.controller;

import com.microcredentials.is.error.InventoryNotFoundException;
import com.microcredentials.is.model.Inventory;
import com.microcredentials.is.service.InventoryService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @Value("${server.port}")
    private String port;

    public static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

    @GetMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name="inventory", fallbackMethod = "fallBackMethod")
    public Inventory getInventoryOfProduct(@PathVariable int productId) throws InventoryNotFoundException {
        LOGGER.debug("Running on port # " + port);
        LOGGER.info("Fetch the inventory of products");
        System.out.println("Running on port # " + port);
        return inventoryService.getInventoryOfProduct(productId);
    }

    public Inventory fallBackMethod(int productId, RuntimeException runtimeException){
        LOGGER.info("Fallback method has been called");
        System.out.println("Fallback method has been called");
        return new Inventory();
    }

    @PostMapping("/addProductCount")
    public String addInventory(@RequestBody Inventory inventory){
        LOGGER.info("Adding inventory into the queue");
        return inventoryService.addInventory(inventory);
    }

    @PostMapping("/addProductsCount")
    public String addInventory(@RequestBody List<Inventory> inventoryList){
        LOGGER.info("Adding list of inventories");
        return inventoryService.addInventory(inventoryList);
    }

    @PostMapping("/reduceProductCount")
    @ResponseStatus(HttpStatus.OK)
    public String reduceProductInventory(int productId, int count) throws InventoryNotFoundException {
        Inventory inventory = getInventoryOfProduct(productId);
        LOGGER.info("Reducing the count of products in the inventory from " + inventory.getQuantity() + " to " + count);
        inventory.setQuantity(inventory.getQuantity()-count);
        inventoryService.reduceInventory(inventory);
        return "Updated the reduction in count and sent for processing via queue";
    }
}
