package ru.otus.shurupov.spring.springdatamongodb.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.springdatamongodb.domain.Author;
import ru.otus.shurupov.spring.springdatamongodb.domain.Book;
import ru.otus.shurupov.spring.springdatamongodb.repository.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final TableRenderer tableRenderer;

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public Optional<Book> getById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public void insert(String name, String authorId, List<String> genres) {
        Author author = authorService.getById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        bookRepository.save(new Book(author, name, genres));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void removeById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void displayList() {
        List<Book> books = getAll();
        render(books);
    }

    @Override
    public void displayById(String id) {
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
                                    "Genre", String.join(", ", book.getGenres())
                            )
                    )
            );
        } else {
            System.out.println("Book with id " + id + " not found");
        }
    }

    @Override
    public void displayByNameFilteredList(String name) {
        List<Book> books = searchByName(name);
        render(books);
    }

    @Override
    public void updateName(String id, String name) {
        Optional<Book> optionalBook = getById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setName(name);
            bookRepository.save(book);
        } else {
            System.out.println("Book with id " + id + " not found");
        }
    }

    @Override
    public void setGenres(String id, List<String> genres) {
        Optional<Book> optionalBook = getById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setGenres(genres);
            bookRepository.save(book);
        } else {
            System.out.println("Book with id " + id + " not found");
        }
    }

    public List<Book> searchByName(String name) {
        return bookRepository.findByNameContainingIgnoreCase(name);
    }

    private void render(List<Book> books) {
        System.out.println(
                tableRenderer.render(
                        "Library book list",
                        Arrays.asList("id", "Name", "Author", "Genres"),
                        (book) -> Arrays.asList(
                                book.getId(),
                                book.getName(),
                                authorService.getAuthorCaption(book.getAuthor()),
                                String.join(", ", book.getGenres())
                        ),
                        books
                )
        );
    }
}
