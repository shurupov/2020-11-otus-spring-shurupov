package ru.otus.shurupov.spring.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.docker.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
