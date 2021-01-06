package ru.otus.shurupov.spring.jpa.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.jpa.dao.BookCommentDao;
import ru.otus.shurupov.spring.jpa.dao.BookDao;
import ru.otus.shurupov.spring.jpa.domain.Book;
import ru.otus.shurupov.spring.jpa.domain.BookComment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentDao bookCommentDao;
    private final BookDao bookDao;
    private final TableRenderer tableRenderer;
    private final BookService bookService;

    @Override
    public long count() {
        return bookCommentDao.count();
    }

    @Override
    public Optional<BookComment> getById(long id) {
        return bookCommentDao.getById(id);
    }

    @Override
    @Transactional
    public void insert(long bookId, String comment) {
        BookComment bookComment = new BookComment();
        Book book = bookDao.getById(bookId).orElseThrow(() -> new RuntimeException("Book not found!"));
        bookComment.setBook(book);
        bookComment.setText(comment);
        bookCommentDao.insert(bookComment);
    }

    @Override
    public List<BookComment> getAll() {
        return bookCommentDao.getAll();
    }

    @Override
    @Transactional
    public void removeById(long id) {
        bookCommentDao.removeById(id);
    }

    @Override
    @Transactional
    public void update(long id, String comment) {
        bookCommentDao.updateById(id, comment);
    }

    @Override
    @Transactional(readOnly = true)
    public void displayList() {
        List<BookComment> comments = getAll();
        System.out.println(
                tableRenderer.render(
                        "Book comments list",
                        Arrays.asList("id", "Book", "Comment"),
                        (comment) -> Arrays.asList(comment.getId(), bookService.getBookCaption(comment.getBook()), comment.getText()),
                        comments
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public void displayById(Long id) {
        Optional<BookComment> optionalAuthor = getById(id);
        if (optionalAuthor.isPresent()) {
            BookComment comment = optionalAuthor.get();
            System.out.println(
                    tableRenderer.render(
                            "Comment",
                            ImmutableMap.of(
                                    "id", comment.getId(),
                                    "Book", bookService.getBookCaption(comment.getBook()),
                                    "Comment", comment.getText()
                            )
                    )
            );
        } else {
            System.out.println("Comment with id " + id + " not found");
        }
    }
}
