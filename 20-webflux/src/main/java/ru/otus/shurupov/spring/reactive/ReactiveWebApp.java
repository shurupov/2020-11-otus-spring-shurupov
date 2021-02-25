package ru.otus.shurupov.spring.reactive;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import ru.otus.shurupov.spring.reactive.domain.Book;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@EnableSwagger2
@EnableMongock
public class ReactiveWebApp {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveWebApp.class);
    }

    @Bean
    public RouterFunction<ServerResponse> composedRoutes() {
        return route()
                .GET(
                        "/api/2/books/{id}",
                        queryParam("id", StringUtils::isNotEmpty),
                        request -> ok().body(new Book(), Book.class)
                    )
                .GET(
                        "/api/2/books",
                        request -> ok().body(Flux.just(new Book(), new Book()), Book.class)
                    )
                .build();
    }
}
