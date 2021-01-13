package ru.otus.shurupov.spring.springdata.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.springdata.repository.BookCommentRepository;
import ru.otus.shurupov.spring.springdata.repository.BookDao;
import ru.otus.shurupov.spring.springdata.domain.Book;
import ru.otus.shurupov.spring.springdata.domain.BookComment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;
    private final BookDao bookDao;
    private final TableRenderer tableRenderer;
    private final BookService bookService;

    @Override
    public long count() {
        return bookCommentRepository.count();
    }

    @Override
    public Optional<BookComment> getById(long id) {
        return bookCommentRepository.findById(id);
    }

    @Override
    @Transactional
    public void insert(long bookId, String comment) {
        BookComment bookComment = new BookComment();
        Book book = bookDao.getById(bookId).orElseThrow(() -> new RuntimeException("Book not found!"));
        bookComment.setBook(book);
        bookComment.setText(comment);
        bookCommentRepository.save(bookComment);
    }

    @Override
    public List<BookComment> getAll() {
        return bookCommentRepository.findAll();
    }

    @Override
    @Transactional
    public void removeById(long id) {
        bookCommentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(long id, String comment) {
        Optional<BookComment> optionalBookComment = bookCommentRepository.findById(id);
        bookCommentRepository.delete(optionalBookComment.orElseThrow(() -> new RuntimeException("Book not found")));
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
