package com.devopsflow.gateway.route;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

//eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IjEyMyIsInJvbGUiOiJbVVNFUl0iLCJpYXQiOjE3MzkzNzEzNDAsImV4cCI6MTczOTM3NDk0MH0._-BWQSciUxY6e0ru26TQpct9z3YietbAI3j6VQzYj9Q
//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    void shouldRouteToAuthService() {
        webClient.get()
                .uri("/auth/common/health-check")  // Auth 프로젝트로 라우팅
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    void shouldRouteToPortalService() {
        webClient.get()
                .uri("/portal/common/health-check")  // Portal 서비스로 라우팅
                .exchange()
                .expectStatus().isUnauthorized();
    }
}
