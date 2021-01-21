package ru.otus.shurupov.spring.springdatamongodb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.springdatamongodb.domain.Book;
import ru.otus.shurupov.spring.springdatamongodb.repository.BookRepository;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final TableRenderer tableRenderer;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchByName(String name) {
        return bookRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void displayList() {
        List<Book> books = getAll();
        render(books);
    }

    @Override
    public void displayFilteredByName(String name) {
        List<Book> books = searchByName(name);
        render(books);
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
