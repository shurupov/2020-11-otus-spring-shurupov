package ru.otus.shurupov.spring.reactive.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.is;

@SpringBootTest
class SummaryControllerTest {
    @Autowired
    private SummaryController summaryController;

    @Test
    public void testSummaryRoute() {
        WebTestClient client = WebTestClient
                .bindToController(summaryController)
                .build();

        client.get()
                .uri("/api/v1/summary")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.books")
                .value(is(7))
                .jsonPath("$.authors")
                .value(is(4));
    }
}