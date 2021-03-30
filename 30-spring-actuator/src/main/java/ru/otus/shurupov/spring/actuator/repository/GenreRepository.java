package ru.otus.shurupov.spring.actuator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.actuator.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
