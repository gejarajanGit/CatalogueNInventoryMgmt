package com.microcredentials.is.service;

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

    public Inventory getInventoryOfProduct(int productId) {
        return restTemplate
                    .getForObject("http://localhost:8082/api/search/product/"+productId,
                            Inventory.class);
    }

    public void addInventory(Inventory inventory) {
        publishMessage(inventory);
    }

    public void addInventory(List<Inventory> inventoryList) {
        for(Inventory inventory : inventoryList){
            publishMessage(inventory);
        }
    }

    public void reduceInventory(Inventory inventory) {
        publishMessage(inventory);
    }

    public void publishMessage(Inventory inventory){
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, inventory);
    }
}
