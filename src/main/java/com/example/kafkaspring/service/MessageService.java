package com.example.kafkaspring.service;

import com.example.kafkaspring.entity.Message;
import com.example.kafkaspring.repository.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message getMessage(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
    }

    public void saveMessage(String message) {
        messageRepository.save(new Message(message, LocalDateTime.now()));
    }


    public Page<Message> getMessages(Integer page, Integer size, Sort.Direction sort, String sortField) {
        return messageRepository.findAll(PageRequest.of(page, size, sort, sortField));
    }
}
