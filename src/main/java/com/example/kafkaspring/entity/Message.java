package com.example.kafkaspring.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private String message;

    private LocalDateTime recived;

    public Message() {
    }

    public Message(String message, LocalDateTime recived) {
        this.message = message;
        this.recived = recived;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getRecived() {
        return recived;
    }

    public void setRecived(LocalDateTime recived) {
        this.recived = recived;
    }
}
