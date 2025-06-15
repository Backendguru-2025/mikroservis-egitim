package com.backendguru.microservice.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.cloud.discovery.enabled=false",
                "spring.cloud.service-registry.auto-registration.enabled=false",
                "spring.security.oauth2.resourceserver.jwt.issuer-uri=disabled"}
)
class OrderControllerIntegrationTest {

    @ServiceConnection
    @Container
    static MySQLContainer mysqlContainer = new MySQLContainer(DockerImageName.parse("mysql:8.0.18"));

    @ServiceConnection
    @Container
    static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("apache/kafka:3.9.1"));

    @LocalServerPort
    private int port;

    //@Test
    void getAllProducts(@Autowired TestRestTemplate restTemplate) {
        NewOrderRequest newOrderRequest = new NewOrderRequest(1L, 1, 1);
        ResponseEntity<NewOrderRequest> response = restTemplate.postForEntity("/v1/orders", newOrderRequest, NewOrderRequest.class);
        System.out.println(response.toString());
        assertThat(response.getStatusCode()).isEqualTo(200);
        List<?> orderResponses = restTemplate.getForObject("/v1/orders", List.class);
        assertThat(orderResponses).isEqualTo(0);
    }
}