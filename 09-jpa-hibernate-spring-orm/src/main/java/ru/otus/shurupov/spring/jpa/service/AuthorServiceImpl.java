package ru.otus.shurupov.spring.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.jpa.dao.AuthorDao;
import ru.otus.shurupov.spring.jpa.domain.Author;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public long count() {
        return authorDao.count();
    }

    @Override
    public Optional<Author> getById(Long id) {
        return authorDao.getById(id);
    }

    @Override
    @Transactional
    public void insert(String firstName, String lastName) {
        authorDao.insert(new Author(firstName, lastName));
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
