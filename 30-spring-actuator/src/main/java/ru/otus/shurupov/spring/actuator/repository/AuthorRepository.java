package ru.otus.shurupov.spring.actuator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.actuator.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
