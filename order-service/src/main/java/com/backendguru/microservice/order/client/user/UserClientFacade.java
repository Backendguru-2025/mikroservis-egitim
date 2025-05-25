package com.backendguru.microservice.order.client.user;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Component
public class UserClientFacade {
    
    private final UserClientService userClientService;

    public UserClientFacade(RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder.baseUrl("http://user-service:8080/").build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        userClientService = factory.createClient(UserClientService.class);
    }

    @CircuitBreaker(name = "get-user-CB", fallbackMethod = "fallbackRuntimeException")
    public UserResponse getUser(Integer userId) {
        simulateFailure();
        return userClientService.getUser(userId);
    }

    private void simulateFailure() {
        if (Math.random() > 0.7) {
            System.out.println("Simulating service failure");
            throw new RuntimeException("Service unavailable");
        }
    }

    private UserResponse fallbackRuntimeException(Integer userId, RuntimeException runtimeException) {
        System.out.println("Circuit breaker fallback activated");
        System.out.println("Fallback RuntimeException :"+ runtimeException);
        return new UserResponse("0","","","");
    }

    private UserResponse fallbackRuntimeException(Integer userId, Throwable throwable) {
        System.out.println("Circuit breaker fallback activated");
        System.out.println("Fallback Throwable :"+ throwable);
        return new UserResponse("-1","","","");
    }
}
