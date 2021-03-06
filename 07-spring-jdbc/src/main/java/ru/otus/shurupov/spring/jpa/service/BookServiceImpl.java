package ru.otus.shurupov.spring.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.jpa.dao.AuthorDao;
import ru.otus.shurupov.spring.jpa.dao.BookDao;
import ru.otus.shurupov.spring.jpa.dao.GenreDao;
import ru.otus.shurupov.spring.jpa.domain.Book;
import ru.otus.shurupov.spring.jpa.domain.dto.BookDto;

import java.util.List;

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
        return bookDao.getById(id);
    }

    @Override
    public void insert(String name, Long authorId, Long genreId) {
        bookDao.insert(new Book(name, authorId, genreId));
    }

    @Override
    public List<BookDto> getAll() {
        return bookDao.getAll();
    }

    @Override
    public void removeById(Long id) {
        bookDao.removeById(id);
    }

    @Override
    public void update(Long id, String name, Long authorId, Long genreId) {
        bookDao.update(new Book(id, authorId, genreId ,name));
    }
}
