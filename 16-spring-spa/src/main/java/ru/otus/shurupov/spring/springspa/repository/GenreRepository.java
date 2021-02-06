package ru.otus.shurupov.spring.springspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.springspa.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
