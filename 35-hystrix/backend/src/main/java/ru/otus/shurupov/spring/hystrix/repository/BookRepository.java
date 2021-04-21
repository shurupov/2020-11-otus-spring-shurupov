package ru.otus.shurupov.spring.hystrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.hystrix.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
