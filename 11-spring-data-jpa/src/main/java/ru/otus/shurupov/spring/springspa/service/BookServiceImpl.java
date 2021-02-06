package ru.otus.shurupov.spring.springdata.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.springdata.repository.AuthorRepository;
import ru.otus.shurupov.spring.springdata.repository.BookRepository;
import ru.otus.shurupov.spring.springdata.repository.GenreRepository;
import ru.otus.shurupov.spring.springdata.domain.Author;
import ru.otus.shurupov.spring.springdata.domain.Book;
import ru.otus.shurupov.spring.springdata.domain.Genre;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final TableRenderer tableRenderer;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public void insert(String name, Long authorId, Long genreId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new RuntimeException("Genre not found"));
        bookRepository.save(new Book(name, author, Collections.singletonList(genre)));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateName(Long id, String name) {
        bookRepository.updateNameById(id, name);
    }

    @Override
    @Transactional(readOnly = true)
    public void displayList() {
        List<Book> books = getAll();
        render(books);
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

    @Override
    @Transactional(readOnly = true)
    public void displayByAuthorFilteredList(String filter) {
        List<Book> books = filterByAuthor(filter);
        render(books);
    }

    @Override
    @Transactional(readOnly = true)
    public void displayByNameFilteredList(String filter) {
        List<Book> books = filterByName(filter);
        render(books);
    }

    @Override
    @Transactional
    public void setGenres(Long bookId, List<Long> genreIds) {
        bookRepository.setGenres(bookId, genreIds);
    }

    private List<Book> filterByAuthor(String filter) {
        return bookRepository.findByAuthorFirstNameContainingIgnoreCaseOrAuthorLastNameContainingIgnoreCase(filter, filter);
    }

    private List<Book> filterByName(String filter) {
        return bookRepository.findByNameContainingIgnoreCase(filter);
    }

    private void render(List<Book> books) {
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
}
