package com.microcredentials.pss.service;

import com.microcredentials.pss.model.Inventory;
import com.microcredentials.pss.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSearchService {

    @Autowired
    InventoryRepository inventoryRepository;
    public List<Inventory> getProducts() {
        return inventoryRepository.findAll();
    }

    public List<Inventory> getProductsInHand() {
        return getProducts().stream()
                .filter(inventory -> inventory.getQuantity()>0)
                .collect(Collectors.toList());
    }
}
