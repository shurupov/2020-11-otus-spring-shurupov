package ru.otus.shurupov.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.batch.domain.postgres.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findAllByNameIn(List<String> names);
}
