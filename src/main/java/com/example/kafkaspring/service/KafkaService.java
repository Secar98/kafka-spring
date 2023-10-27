package com.example.kafkaspring.service;

import com.example.kafkaspring.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service for sending and receiving messages from Kafka.
 * */
@Service
public class KafkaService {

    private final MessageService messageService;
    private final KafkaTemplate<String, Message> kafkaTemplate;
    private final static String TOPIC = "messages";

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaService.class);

    public KafkaService(
            MessageService messageService,
            KafkaTemplate<String, Message> kafkaTemplate) {
        this.messageService = messageService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, new Message(message, LocalDateTime.now()));
    }

    @KafkaListener(
            topics = TOPIC,
            containerFactory = "messageKafkaListenerContainerFactory")
    public void greetingListener(Message message) {
        LOGGER.info("Received message: {}", message.getMessage());
        messageService.saveMessage(message.getMessage());
    }

}
