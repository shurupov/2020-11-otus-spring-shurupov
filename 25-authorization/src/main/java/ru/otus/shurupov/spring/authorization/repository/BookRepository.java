package ru.otus.shurupov.spring.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.authorization.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
