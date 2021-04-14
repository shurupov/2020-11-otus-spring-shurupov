package ru.otus.shurupov.spring.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.docker.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
