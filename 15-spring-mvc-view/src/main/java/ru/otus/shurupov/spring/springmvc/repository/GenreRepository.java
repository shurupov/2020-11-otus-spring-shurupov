package ru.otus.shurupov.spring.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.springmvc.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
