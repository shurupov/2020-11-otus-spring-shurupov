package ru.otus.shurupov.spring.hystrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.hystrix.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
