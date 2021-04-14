package ru.otus.shurupov.spring.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.docker.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByUsername(String username);
}
