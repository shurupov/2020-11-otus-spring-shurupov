package ru.otus.shurupov.spring.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.authentication.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
