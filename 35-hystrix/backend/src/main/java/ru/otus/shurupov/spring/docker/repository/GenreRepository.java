package ru.otus.shurupov.spring.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.docker.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
