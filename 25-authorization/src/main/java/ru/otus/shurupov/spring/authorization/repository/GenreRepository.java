package ru.otus.shurupov.spring.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.authorization.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
