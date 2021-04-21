package ru.otus.shurupov.spring.hystrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.hystrix.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
