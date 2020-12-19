package ru.otus.shurupov.spring.jdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.jdbc.dao.BookDao;
import ru.otus.shurupov.spring.jdbc.domain.Book;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public int count() {
        return bookDao.count();
    }

    @Override
    public Book getById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public void insert(String name) {
        bookDao.insert(new Book(name));
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
