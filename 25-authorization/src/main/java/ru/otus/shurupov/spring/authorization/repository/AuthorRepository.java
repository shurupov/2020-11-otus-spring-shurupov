package ru.otus.shurupov.spring.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.authorization.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
