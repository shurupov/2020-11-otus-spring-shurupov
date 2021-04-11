package ru.otus.shurupov.spring.actuator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.actuator.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByUsername(String username);
}
