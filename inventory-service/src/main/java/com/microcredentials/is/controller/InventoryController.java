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

    @GetMapping("/products/{pid}")
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getInventoryOfProduct(Long pid){
        return inventoryService.getInventoryOfProduct(pid);
    }

    @PutMapping("/addProductCount")
    @ResponseStatus(HttpStatus.OK)
    public void addInventory(@RequestBody Inventory inventory){
        inventoryService.addInventory(inventory);
    }

    @PutMapping("/addProductsCount")
    @ResponseStatus(HttpStatus.OK)
    public void addInventory(@RequestBody List<Inventory> inventory){
        inventoryService.addInventory(inventory);
    }

    @PostMapping("/reduceProductCount")
    @ResponseStatus(HttpStatus.OK)
    public void reduceProductInventory(@RequestBody Inventory inventory){
        inventoryService.reduceInventory(inventory);
    }

}
