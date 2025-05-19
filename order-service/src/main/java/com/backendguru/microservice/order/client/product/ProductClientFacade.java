package com.backendguru.microservice.order.client.product;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.math.BigDecimal;

@Component
public class ProductClientFacade {
    
    private final ProductClientService productClientService;

    public ProductClientFacade() {
        RestClient restClient = RestClient.builder().baseUrl("http://product-service:8080/").build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        productClientService = factory.createClient(ProductClientService.class);
    }

    @CircuitBreaker(name = "get-product-CB", fallbackMethod = "fallbackRuntimeException")
    public ProductResponse getProduct(Long productId) {
        simulateFailure();
        return productClientService.getProduct(productId);
    }

    private void simulateFailure() {
        if (Math.random() > 0.7) {
            System.out.println("Simulating service failure");
            throw new RuntimeException("Service unavailable");
        }
    }

    private ProductResponse fallbackRuntimeException(Long productId, RuntimeException runtimeException) {
        System.out.println("Circuit breaker fallback activated");
        System.out.println("Fallback RuntimeException :"+ runtimeException);
        return new ProductResponse(0,"dummy-product", "lorem ipsum", BigDecimal.ZERO);
    }

    private ProductResponse fallbackRuntimeException(Long productId, Throwable throwable) {
        System.out.println("Circuit breaker fallback activated");
        System.out.println("Fallback Throwable :"+ throwable);
        return new ProductResponse(-1,"dummy-product", "lorem ipsum", BigDecimal.ONE.negate());
    }
}
