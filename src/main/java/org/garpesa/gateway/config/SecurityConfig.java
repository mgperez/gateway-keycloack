package org.garpesa.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.garpesa.gateway.GatewayController.SECURED;
import static org.garpesa.gateway.GatewayController.UNSECURED;

/**
 * https://github.com/bassmake/spring-cloud-gateway-with-keycloak
 * https://docs.spring.io/spring-security/site/docs/5.2.2.RELEASE/reference/html/webflux-oauth2.html
 * https://andifalk.github.io/reactive-spring-security-5-workshop/workshop-tutorial.html
 */
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {


    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
        .csrf()
        .disable()
        .authorizeExchange()
        .matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .matchers(EndpointRequest.to("health")).permitAll()
        .matchers(EndpointRequest.to("info")).permitAll()
        .pathMatchers(SECURED).authenticated()
        .pathMatchers(UNSECURED).permitAll()
        .pathMatchers("/role").hasRole("ADMIN")
        .anyExchange().authenticated()
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
