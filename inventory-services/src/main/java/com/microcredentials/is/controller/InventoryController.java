package com.microcredentials.is.controller;

import com.microcredentials.is.model.Inventory;
import com.microcredentials.is.service.InventoryService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @GetMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name="inventory", fallbackMethod = "fallBackMethod")
    public Inventory getInventoryOfProduct(@PathVariable int productId){
        System.out.println("Running on port # " + port);
        return inventoryService.getInventoryOfProduct(productId);
    }

    public Inventory fallBackMethod(int productId, RuntimeException runtimeException){
        return new Inventory();
    }

    @PostMapping("/addProductCount")
    public Inventory addInventory(@RequestBody Inventory inventory){
        return inventoryService.addInventory(inventory);
    }

    @PostMapping("/addProductsCount")
    public String addInventory(@RequestBody List<Inventory> inventoryList){
        inventoryService.addInventory(inventoryList);
        return "Added the Inventory list";
    }

    @PostMapping("/reduceProductCount")
    @ResponseStatus(HttpStatus.OK)
    public String reduceProductInventory(int productId, int count){
        Inventory inventory = getInventoryOfProduct(productId);
        inventory.setQuantity(inventory.getQuantity()-count);
        inventoryService.reduceInventory(inventory);
        return "Updated the reduction in count";
    }
}
