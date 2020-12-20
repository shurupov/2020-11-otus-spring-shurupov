package ru.otus.shurupov.spring.jdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.jdbc.dao.AuthorDao;
import ru.otus.shurupov.spring.jdbc.dao.BookDao;
import ru.otus.shurupov.spring.jdbc.dao.GenreDao;
import ru.otus.shurupov.spring.jdbc.domain.Author;
import ru.otus.shurupov.spring.jdbc.domain.Book;
import ru.otus.shurupov.spring.jdbc.domain.dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public int count() {
        return bookDao.count();
    }

    @Override
    public BookDto getById(Long id) {
        return map(bookDao.getById(id));
    }

    @Override
    public void insert(String name, Long authorId, Long genreId) {
        bookDao.insert(new Book(name, authorId, genreId));
    }

    @Override
    public List<BookDto> getAll() {
        return bookDao.getAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public void removeById(Long id) {
        bookDao.removeById(id);
    }

    @Override
    public void update(Long id, String name, Long authorId, Long genreId) {
        bookDao.update(new Book(id, authorId, genreId ,name));
    }

    private BookDto map(Book book) {
        return new BookDto(book, authorDao.getById(book.getAuthorId()), genreDao.getById(book.getGenreId()));
    }
}
