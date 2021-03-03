package ru.otus.shurupov.spring.reactive.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.hamcrest.Matchers.is;

@SpringBootTest
public class SummaryRouterFunctionTest {

    @Autowired
    private RouterFunction summaryRoute;

    @Test
    public void testSummaryRoute() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(summaryRoute)
                .build();

        client.get()
                .uri("/api/v2/summary")
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
