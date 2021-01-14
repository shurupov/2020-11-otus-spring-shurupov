package ru.otus.shurupov.spring.jpa.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.jpa.dao.AuthorDao;
import ru.otus.shurupov.spring.jpa.dao.BookDao;
import ru.otus.shurupov.spring.jpa.dao.GenreDao;
import ru.otus.shurupov.spring.jpa.domain.Author;
import ru.otus.shurupov.spring.jpa.domain.Book;
import ru.otus.shurupov.spring.jpa.domain.Genre;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final TableRenderer tableRenderer;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public long count() {
        return bookDao.count();
    }

    @Override
    public Optional<Book> getById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    @Transactional
    public void insert(String name, Long authorId, Long genreId) {
        Author author = authorDao.getById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        Genre genre = genreDao.getById(genreId).orElseThrow(() -> new RuntimeException("Genre not found"));
        bookDao.insert(new Book(name, author, Collections.singletonList(genre)));
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        Optional<Book> bookOptional = bookDao.getById(id);
        bookDao.remove(bookOptional.orElseThrow(() -> new RuntimeException("Book not found")));
    }

    @Override
    @Transactional
    public void updateName(Long id, String name) {
        bookDao.updateNameById(id, name);
    }

    @Override
    @Transactional(readOnly = true)
    public void displayList() {
        List<Book> books = getAll();
        System.out.println(
                tableRenderer.render(
                        "Library book list",
                        Arrays.asList("id", "Name", "Author", "Genre"),
                        (book) -> Arrays.asList(
                                book.getId().toString(),
                                book.getName(),
                                authorService.getAuthorCaption(book.getAuthor()),
                                genreService.getGenreCaption(book.getGenres())
                        ),
                        books
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public void displayById(Long id) {
        Optional<Book> optionalBook = getById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            System.out.println(
                    tableRenderer.render(
                            "Book",
                            ImmutableMap.of(
                                    "id", book.getId(),
                                    "Name", book.getName(),
                                    "Author", authorService.getAuthorCaption(book.getAuthor()),
                                    "Genre", genreService.getGenreCaption(book.getGenres())
                            )
                    )
            );
            System.out.println(
                    tableRenderer.render(
                            "Book Comments",
                            Arrays.asList("id", "Comment"),
                            (comment) -> Arrays.asList(comment.getId(), comment.getText()),
                            book.getComments()
                    )
            );
        } else {
            System.out.println("Book with id " + id + " not found");
        }
    }

    @Override
    public String getBookCaption(Book book) {
        return String.format("%s (%s)", book.getName(), book.getId());
    }
}
