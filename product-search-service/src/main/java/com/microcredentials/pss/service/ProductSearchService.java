package com.microcredentials.pss.service;

import com.microcredentials.pss.error.ProductNotFoundException;
import com.microcredentials.pss.model.Inventory;
import com.microcredentials.pss.model.ProductCatalogue;
import com.microcredentials.pss.mq.MQConfig;
import com.microcredentials.pss.repository.InventoryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductSearchService {

    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    RestTemplate restTemplate;

    public List<Inventory> getProducts() throws ProductNotFoundException {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        if (inventoryList.size() == 0)
            throw new ProductNotFoundException("No product found currently :(");
        return inventoryList;
    }

    public List<Inventory> getProductsInHand() throws ProductNotFoundException {
        List<Inventory> inventoryList = getProducts().stream()
                .filter(inventory -> inventory.getQuantity() > 0)
                .collect(Collectors.toList());
        if (inventoryList.size() == 0)
            throw new ProductNotFoundException("No in-hand product found currently :(");
        return inventoryList;
    }

    public Inventory getProductById(int productId) throws ProductNotFoundException {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        if (!inventory.isPresent())
            throw new ProductNotFoundException("No product found with product Id: " + productId + " :(");
        return inventory.get();
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(Inventory inventory) {
        System.out.println("Message received");
        inventoryRepository.save(inventory);
        System.out.println("Message persisted");
    }

    public ProductCatalogue getProductCatalogue(String brand, String color) throws ProductNotFoundException {
        ProductCatalogue productCatalogue = null;
        try {
            productCatalogue = restTemplate
                    .getForObject("http://CATALOGUE-SERVICE/api/catalogue/product?brand=" + brand + "&color=" + color,
                            ProductCatalogue.class);
        } catch (Exception ex) {
            throw new ProductNotFoundException("No Catalogue found with the brand : " + brand + " and color : " + color + " :(");
        }
        if (productCatalogue == null)
            throw new ProductNotFoundException("No Catalogue found with the brand : " + brand + " and color : " + color + " :(");
        return productCatalogue;
    }
}
