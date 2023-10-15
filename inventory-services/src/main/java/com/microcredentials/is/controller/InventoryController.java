package com.microcredentials.is.controller;

import com.microcredentials.is.model.Inventory;
import com.microcredentials.is.service.InventoryService;
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
    public Inventory getInventoryOfProduct(@PathVariable int productId){
        return inventoryService.getInventoryOfProduct(productId);
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
