package ru.otus.shurupov.spring.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.authentication.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
