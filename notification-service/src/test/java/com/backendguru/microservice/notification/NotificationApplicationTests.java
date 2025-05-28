package com.backendguru.microservice.notification;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
		"spring.datasource.driverClassName=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
})
class NotificationApplicationTests {

	static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("apache/kafka:3.9.1"));

	static {
		kafkaContainer.start();
		System.setProperty("spring.kafka.consumer.bootstrap-servers", kafkaContainer.getBootstrapServers());
	}

	@Test
	void contextLoads() {
	}

}
