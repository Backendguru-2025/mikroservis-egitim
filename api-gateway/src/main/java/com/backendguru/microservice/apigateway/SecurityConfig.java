package com.backendguru.microservice.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/actuator/**").permitAll() // Örnek: Actuator uç noktalarına izin ver
                        // Gerekirse belirli yol korumalarını tanımlayın veya varsayılan olarak kimlik doğrulamayı kullanın
                        .anyExchange().authenticated()
                )
                //.oauth2Login(Customizer.withDefaults()) // Keycloak aracılığıyla login akışını başlatmak için
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())); // Bearer belirteçlerini (JWT'ler) doğrulamak için
        //http.csrf(csrf -> csrf.disable()); // Durumsuz API'ler için yaygın, CSRF ihtiyaçlarını dikkatlice değerlendirin
        return http.build();
    }
}
