package com.microcredentials.is.service;

import com.microcredentials.is.model.Inventory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class QueuePublisherService {
    public void sendMessagesToQueue(Inventory inventory) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(15671);
        factory.setUsername("guest");
        factory.setPassword("guest");
        try (Connection connection = factory.newConnection()){
            Channel channel = connection.createChannel();
            channel.queueDeclare("microcredentials", false, false, false, null);
            channel.basicPublish("", "microcredentials", false, null, inventory.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println("Message has been sent");
        }
    }
}
