package ru.otus.shurupov.spring.springspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.springspa.domain.BookComment;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {
}
