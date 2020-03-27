package org.garpesa.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.garpesa.gateway.GatewayController.SECURED;
import static org.garpesa.gateway.GatewayController.UNSECURED;

/**
 * https://github.com/bassmake/spring-cloud-gateway-with-keycloak
 * https://docs.spring.io/spring-security/site/docs/5.1.0.RELEASE/reference/html/webflux-oauth2.html
 */
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
                .pathMatchers(SECURED).authenticated()
                .pathMatchers(UNSECURED).permitAll()
                .pathMatchers("/actuator/**").permitAll()
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }



    /*
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/message/**").hasAuthority("SCOPE_message:read")
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }

     */

    /*
    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
        return http.httpBasic().and()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/anything/**").authenticated()
                .anyExchange().permitAll()
                .and()
                .build();
    }

     */


}
