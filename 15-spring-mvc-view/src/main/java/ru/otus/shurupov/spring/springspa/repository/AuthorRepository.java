package ru.otus.shurupov.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.springdata.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
