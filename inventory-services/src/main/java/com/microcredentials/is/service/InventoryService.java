package com.microcredentials.is.service;

import com.microcredentials.is.error.InventoryNotFoundException;
import com.microcredentials.is.model.Inventory;
import com.microcredentials.is.mq.MQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    RabbitTemplate template;
    @Autowired
    RestTemplate restTemplate;

    public Inventory getInventoryOfProduct(int productId) throws InventoryNotFoundException {
        Inventory inventory = null;
        try{
            inventory = restTemplate
                    .getForObject("http://PRODUCT-SEARCH-SERVICE/api/search/product/"+productId,
                            Inventory.class);
        }catch(Exception ex){
            if(((HttpClientErrorException.NotFound)ex).getStatusCode().value() == 404)
                throw new InventoryNotFoundException("No inventory found with product id " + productId);
        }
        return inventory;
    }

    public String addInventory(Inventory inventory) {
        publishMessage(inventory);
        return "Inventory data has been sent out to queue for insertion with the inventory data " + inventory.toString();
    }

    public String addInventory(List<Inventory> inventoryList) {
        for(Inventory inventory : inventoryList){
            publishMessage(inventory);
        }
        return "Inventory list has been sent out to queue for insertion with the inventory data " + inventoryList.toString();
    }

    public String reduceInventory(Inventory inventory) {
        publishMessage(inventory);
        return "Count reduction has been sent out to queue to be updated with the inventory data " + inventory.toString();
    }

    public void publishMessage(Inventory inventory){
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, inventory);
    }
}
