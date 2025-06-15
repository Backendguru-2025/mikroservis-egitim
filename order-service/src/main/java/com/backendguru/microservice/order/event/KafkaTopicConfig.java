package com.backendguru.microservice.order.event;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String ORDERS_TOPIC = "order.events";
    public static final String SAGA_TOPIC = "saga-topic";

    @Bean
    public NewTopic ordersTopic() {
        return TopicBuilder.name(ORDERS_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic sagaTopic() {
        return TopicBuilder.name(SAGA_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
