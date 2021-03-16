package ru.otus.shurupov.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.batch.domain.postgres.PostgresAuthor;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<PostgresAuthor, Long> {
    Optional<PostgresAuthor> findByFirstNameAndLastName(String firstName, String lastName);
}
