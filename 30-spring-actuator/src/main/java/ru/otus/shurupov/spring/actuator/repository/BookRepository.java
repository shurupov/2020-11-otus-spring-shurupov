package ru.otus.shurupov.spring.actuator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.actuator.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
