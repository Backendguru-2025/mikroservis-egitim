package com.backendguru.microservice.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@SpringBootTest(properties = {
    "spring.cloud.discovery.enabled=false",
    "spring.cloud.service-registry.auto-registration.enabled=false"
})
class OrderServiceApplicationTests {

	@Container
	@ServiceConnection
	static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

	@Test
	void contextLoads() {
	}

}
