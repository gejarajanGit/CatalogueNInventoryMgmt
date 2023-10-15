package com.microcredentials.is.service;

import com.microcredentials.is.model.Inventory;
import com.microcredentials.is.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    QueuePublisherService queuePublisherService;

    public List<Inventory> getInventoryOfProduct(Long pid){
        return inventoryRepository.findByProductId(pid);
    }

    public void addInventory(Inventory inventory){
        //inventoryRepository.save(inventory);
        try {
            queuePublisherService.sendMessagesToQueue(inventory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public void addInventory(List<Inventory> inventoryList){
        //inventoryRepository.saveAll(inventoryList);
        try {
            for(Inventory inventory : inventoryList){
                queuePublisherService.sendMessagesToQueue(inventory);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public void reduceInventory(Inventory inventory){
        //inventoryRepository.save(inventory);
        try {
            queuePublisherService.sendMessagesToQueue(inventory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
