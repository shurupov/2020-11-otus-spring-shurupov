package ru.otus.shurupov.spring.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.jpa.dao.AuthorDao;
import ru.otus.shurupov.spring.jpa.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public int count() {
        return authorDao.count();
    }

    @Override
    public Author getById(Long id) {
        return authorDao.getById(id);
    }

    @Override
    public void insert(String firstName, String lastName) {
        authorDao.insert(new Author(firstName, lastName));
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
