package ru.otus.shurupov.spring.authentication.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.spring.authentication.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {

    List<BookComment> getAllByBookId(Long bookId, Sort sort);
}
