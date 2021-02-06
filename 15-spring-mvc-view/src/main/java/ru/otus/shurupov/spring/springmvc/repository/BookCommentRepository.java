package ru.otus.shurupov.spring.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.springmvc.domain.BookComment;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {
}
