package ru.otus.shurupov.spring.springspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.otus.shurupov.spring.springspa.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
}
