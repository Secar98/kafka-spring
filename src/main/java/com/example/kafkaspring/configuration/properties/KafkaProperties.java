package com.example.kafkaspring.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spring.kafka")
public record KafkaProperties(List<String> bootstrapServers, Consumer consumer) {
    @ConfigurationProperties("spring.kafka.consumer")
    public record Consumer(String groupId) {}
}
