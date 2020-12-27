package ru.otus.shurupov.spring.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.jpa.dao.BookDao;
import ru.otus.shurupov.spring.jpa.domain.Author;
import ru.otus.shurupov.spring.jpa.domain.Book;
import ru.otus.shurupov.spring.jpa.domain.Genre;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public long count() {
        return bookDao.count();
    }

    @Override
    public Optional<Book> getById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public void insert(String name, Long authorId, Long genreId) {
        Author author = new Author();
        author.setId(authorId);
        Genre genre = new Genre();
        genre.setId(genreId);
        bookDao.insert(new Book(name, author, genre));
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public void removeById(Long id) {
        bookDao.removeById(id);
    }

    @Override
    public void updateName(Long id, String name) {
        bookDao.updateNameById(id, name);
    }
}
