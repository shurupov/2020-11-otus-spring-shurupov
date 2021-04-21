package ru.otus.shurupov.spring.hystrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.hystrix.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByUsername(String username);
}
