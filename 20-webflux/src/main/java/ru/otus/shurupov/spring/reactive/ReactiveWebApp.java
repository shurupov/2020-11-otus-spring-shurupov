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
import ru.otus.shurupov.spring.reactive.repository.BookRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
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
    public RouterFunction<ServerResponse> bookRoutes(BookRepository bookRepository) {
        return route()
                .GET(
                        "/api/2/books",
                        request -> ok().body(bookRepository.findAll(), Book.class)
                    )
                .GET(
                        "/api/2/books/{id}",
                        request -> bookRepository.findById(request.pathVariable("id"))
                            .flatMap(book -> ok().body(fromValue(book)))
                    )
                .build();
    }
}
