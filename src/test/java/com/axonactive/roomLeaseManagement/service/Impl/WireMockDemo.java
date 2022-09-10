package com.axonactive.roomLeaseManagement.service.Impl;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockDemo {
    private WireMockServer wireMockServer;
    private RestTemplate restTemplate;

    private String apiLink() {
        return String.format("http://localhost:%d/api/onwer/1", wireMockServer.port());
    }
    @BeforeEach
    void setUpWireMock() {
        this.wireMockServer = new WireMockServer(options().dynamicPort());
        this.restTemplate = new RestTemplate();
        wireMockServer.start();
        configureFor(this.wireMockServer.port());
    }

    //    "{\"number\": 1, \"type\": \"big\"}"
    @Test
    void creatRoom() {
        givenThat(get(urlPathEqualTo("/api/owner/1"))
                .willReturn(aResponse()
                        .withBody("{\"id\":1, \" firstName\": \"Duong\", \"lastName\": \"Phan\",\"phoneNumber\":\"2222\",\"email\":\"duong@gmail.com\",\"gender\":\"male\"}")
                ));

        ResponseEntity<String> response = restTemplate.getForEntity(apiLink(),String.class);



    }

    @AfterEach
    void stopWireMock() {
        wireMockServer.stop();
    }
}
