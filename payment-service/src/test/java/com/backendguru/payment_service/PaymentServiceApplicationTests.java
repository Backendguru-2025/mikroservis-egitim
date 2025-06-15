package com.backendguru.payment_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
class PaymentServiceApplicationTests {


	static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("apache/kafka:3.9.1"));

	static {
		kafkaContainer.start();
		System.setProperty("spring.kafka.bootstrap-servers", kafkaContainer.getBootstrapServers());
		System.setProperty("spring.kafka.consumer.bootstrap-servers", kafkaContainer.getBootstrapServers());
	}
	@Test
	void contextLoads() {
	}

}
