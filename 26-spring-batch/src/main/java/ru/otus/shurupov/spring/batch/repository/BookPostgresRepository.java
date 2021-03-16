package ru.otus.shurupov.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.batch.domain.postgres.PostgresBook;

public interface BookPostgresRepository extends JpaRepository<PostgresBook, Long> {
}
