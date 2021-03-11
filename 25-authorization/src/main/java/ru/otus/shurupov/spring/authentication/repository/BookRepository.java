package ru.otus.shurupov.spring.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.authentication.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
