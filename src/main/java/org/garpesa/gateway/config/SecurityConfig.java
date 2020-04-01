package org.garpesa.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.garpesa.gateway.GatewayController.SECURED;
import static org.garpesa.gateway.GatewayController.UNSECURED;

/**
 * https://www.baeldung.com/spring-webflux
 * https://www.baeldung.com/rest-api-spring-oauth2-angular
 *
 * https://github.com/bassmake/spring-cloud-gateway-with-keycloak
 *
 * https://auth0.com/blog/introduction-getting-started-with-spring-webflux-api/
 * https://github.com/auth0-samples/auth0-spring-security5-api-sample/blob/master/01-Authorization-WebFlux/src/main/java/com/auth0/example/security/SecurityConfig.java
 * 
 * https://docs.spring.io/spring-security/site/docs/5.2.2.RELEASE/reference/html/webflux-oauth2.html
 * https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-security-webflux
 * 
 * https://andifalk.github.io/reactive-spring-security-5-workshop/workshop-tutorial.html
 *
 * Kubernetes
 * https://developer.okta.com/blog/2019/04/01/spring-boot-microservices-with-kubernetes
 *
 *
 * https://developer.okta.com/blog/2018/09/24/reactive-apis-with-spring-webflux
 *
 * Spring Boot Admin server
 * https://www.javadevjournal.com/spring-boot/spring-boot-admin/
 * @Value
 * http://www.myjavablog.com/2018/12/29/spring-boot-spring-security-h2-database/
 *
 * RBAC: Database Design for Role-Based
 * https://www.javaguides.net/2018/09/spring-boot-spring-mvc-role-based-spring-security-jpa-thymeleaf-mysql-tutorial.html
 * https://mkyong.com/spring-boot/spring-rest-spring-security-example/
 *
 * Using @ConfigurationProperties to separate service and configuration
 * https://tuhrig.de/using-configurationproperties-to-separate-service-and-configuration
 *
 * How to Load Application Properties from Database
 * https://www.opencodez.com/java/how-to-load-application-properties-from-database.htm
 */
@Slf4j
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    //@Value("${spring.security.oauth2.resourceserver.jwk.issuer-uri}")
    @Value("${oidc.issuer-uri}")
    private String issuerUri;

    @Value( "${remote.home}" )
    private String audience;

    // Disable webflux security
    /*
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        //return  http.authorizeExchange().anyExchange().permitAll().and().build();
        return http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2Login()
                .and()
                .oauth2ResourceServer()
                .jwt().and().and().build();
    }*/



    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        https://tools.ietf.org/html/rfc6750#section-3.1
        */
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
        .pathMatchers(HttpMethod.GET,"/movies/**").permitAll()
        .anyExchange().authenticated()
        .and()
        .oauth2ResourceServer()
        .jwt();

        log.debug("issuerUri: ", issuerUri);

        //http.csrf().disable();
        //http.headers().frameOptions().disable();

        return http.build();
    }

    /*
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders.fromOidcIssuerLocation(issuerUri);
    }

     */

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
