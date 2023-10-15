package com.microcredentials.pss.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microcredentials.pss.model.Inventory;
import com.microcredentials.pss.repository.InventoryRepository;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class QueueConsumerService {

    @Autowired
    InventoryRepository inventoryRepository;

    public void consumeMessagesFromQueue() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(15671);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("microcredentials", false, false, false, null);
        String message = channel.basicConsume("microcredentials", true, (s, delivery) -> {
            delivery.getBody();
        }, s -> { });
        System.out.println("Received message : " + message);
        ObjectMapper mapper = new ObjectMapper();
        updateInventory(mapper.readValue(message, Inventory.class));
    }

    public void updateInventory(Inventory inventory){
        inventoryRepository.save(inventory);
    }
}
