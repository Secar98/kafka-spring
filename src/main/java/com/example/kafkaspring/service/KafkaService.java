package com.example.kafkaspring.service;

import com.example.kafkaspring.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Service for sending and receiving messages from Kafka.
 * */
@Service
public class KafkaService<T> {

    private final MessageService messageService;
    private final KafkaTemplate<String, T> kafkaTemplate;
    private final static String TOPIC = "messages";
    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaService.class);

    public KafkaService(
            MessageService messageService,
            KafkaTemplate<String, T> kafkaTemplate) {
        this.messageService = messageService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, T clazz) {
        kafkaTemplate.send(topic, clazz).thenApply(
                result -> {
                    LOGGER.info("Sent message: {}", clazz.getClass());
                    return result;
                });
    }

    @KafkaListener(
            topics = TOPIC,
            containerFactory = "messageKafkaListenerContainerFactory")
    public void greetingListener(Message message) {
        LOGGER.info("Received message: {}", message.getMessage());
        messageService.saveMessage(message.getMessage());
    }

}
