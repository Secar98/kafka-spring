package com.example.kafkaspring;

import com.example.kafkaspring.entity.Message;
import com.example.kafkaspring.service.KafkaService;
import com.example.kafkaspring.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaServiceTest {
     @Mock
     private MessageService messageService;
     @Mock
     private KafkaTemplate<String, Message> kafkaTemplate;

     @InjectMocks
     private KafkaService<Message> kafkaService;

     @Test
     void saveMessage() {
         messageService.saveMessage("test");
         verify(messageService).saveMessage(any());
     }

     @Test
     void sendMessage() {
         kafkaService.sendMessage("messages", new Message("test", LocalDateTime.now()));
         verify(kafkaTemplate).send(any(), any());
     }

}
