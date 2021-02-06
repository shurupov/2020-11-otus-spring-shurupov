package ru.otus.shurupov.spring.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.springmvc.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
