package ru.otus.shurupov.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.springdata.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
