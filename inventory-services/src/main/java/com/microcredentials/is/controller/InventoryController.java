package com.microcredentials.is.controller;

import com.microcredentials.is.model.Inventory;
import com.microcredentials.is.service.InventoryService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name="inventory", fallbackMethod = "fallBackMethod")
    public Inventory getInventoryOfProduct(@PathVariable int productId){
        return inventoryService.getInventoryOfProduct(productId);
    }

    public Inventory fallBackMethod(int productId, RuntimeException runtimeException){
        return new Inventory();
    }

    @PostMapping("/addProductCount")
    public void addInventory(@RequestBody Inventory inventory){
        inventoryService.addInventory(inventory);
    }

    @PostMapping("/addProductsCount")
    public void addInventory(@RequestBody List<Inventory> inventory){
        inventoryService.addInventory(inventory);
    }

    @PostMapping("/reduceProductCount")
    @ResponseStatus(HttpStatus.OK)
    public void reduceProductInventory(int productId, int count){
        Inventory inventory = getInventoryOfProduct(productId);
        inventory.setQuantity(inventory.getQuantity()-count);
        inventoryService.reduceInventory(inventory);
    }
}
