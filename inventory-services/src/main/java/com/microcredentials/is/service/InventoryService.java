package com.microcredentials.is.service;

import com.microcredentials.is.error.InventoryNotFoundException;
import com.microcredentials.is.model.Inventory;
import com.microcredentials.is.mq.MQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    RabbitTemplate template;
    @Autowired
    RestTemplate restTemplate;

    public Inventory getInventoryOfProduct(int productId) throws InventoryNotFoundException {
        Inventory inventory = restTemplate
                    .getForObject("http://PRODUCT-SEARCH-SERVICE/api/search/product/"+productId,
                            Inventory.class);
        if(inventory==null)
            throw new InventoryNotFoundException("No inventory found with product Id");
        return inventory;
    }

    public String addInventory(Inventory inventory) {
        publishMessage(inventory);
        return "Inventory data has been sent out to queue for insertion";
    }

    public String addInventory(List<Inventory> inventoryList) {
        for(Inventory inventory : inventoryList){
            publishMessage(inventory);
        }
        return "Inventory list  has been sent out to queue for insertion";
    }

    public String reduceInventory(Inventory inventory) {
        publishMessage(inventory);
        return "Count reduction has been sent out to queue to be updated";
    }

    public void publishMessage(Inventory inventory){
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, inventory);
    }
}
