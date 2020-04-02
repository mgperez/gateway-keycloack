package org.garpesa.gateway;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.garpesa.gateway.GatewayController.UNSECURED;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GatewayAppSpec {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void thatUnsecuredEndpointIsAvailable() {
        log.debug("thatUnsecuredEndpointIsAvailable");
        webClient
                .get()
                .uri(UNSECURED)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

}
