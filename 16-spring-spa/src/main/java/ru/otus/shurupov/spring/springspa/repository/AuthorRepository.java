package ru.otus.shurupov.spring.springspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.springspa.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
