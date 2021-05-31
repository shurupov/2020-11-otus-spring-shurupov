package ru.otus.shurupov.jointpurchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.jointpurchase.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByUsername(String username);
}
