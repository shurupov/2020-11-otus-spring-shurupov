package ru.otus.shurupov.spring.reactive;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.shurupov.spring.reactive.domain.Book;
import ru.otus.shurupov.spring.reactive.domain.dto.BookDto;
import ru.otus.shurupov.spring.reactive.repository.AuthorRepository;
import ru.otus.shurupov.spring.reactive.repository.BookRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
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
    public RouterFunction<ServerResponse> bookRoutes(BookRepository bookRepository, AuthorRepository authorRepository) {
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
                .POST(
                        "/api/2/books",
                        accept(APPLICATION_JSON),
                        request -> request.bodyToMono(BookDto.class)
                                .flatMap(book -> authorRepository.findById(book.getAuthorId())
                                        .flatMap(author -> {
                                            Book book1 = new Book();
                                            book1.setAuthor(author);
                                            book1.setName(book.getName());
                                            book1.setGenres(book.getGenres());
                                            book1.setComments(book.getComments());
                                            return Mono.just(book1);
                                        }).flatMap(bookRepository::save))
                                .flatMap(book -> ok().body(fromValue(book)))
                )
                .build();
    }
}
