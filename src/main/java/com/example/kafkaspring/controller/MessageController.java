package com.example.kafkaspring.controller;

import com.example.kafkaspring.entity.Message;
import com.example.kafkaspring.service.KafkaService;
import com.example.kafkaspring.service.MessageService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final KafkaService kafkaService;
    private final MessageService messageService;

    public MessageController(KafkaService kafkaService, MessageService messageService) {
        this.kafkaService = kafkaService;
        this.messageService = messageService;
    }

    @PostMapping
    public void sendMessage(@RequestBody Message message) {
        kafkaService.sendMessage(message.getMessage());
    }

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable Long id) {
        return messageService.getMessage(id);
    }

    @GetMapping
    public List<Message> getMessages(@RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit,
                                     @RequestParam(defaultValue = "desc") String sort,
                                     @RequestParam(defaultValue = "id") String sortField) {
        return messageService
                .getMessages(page, limit, Sort.Direction.fromString(sort), sortField)
                .stream()
                .toList();
    }
}
