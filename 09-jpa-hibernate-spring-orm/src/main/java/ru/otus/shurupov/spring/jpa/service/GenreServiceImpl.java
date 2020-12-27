package ru.otus.shurupov.spring.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.jpa.dao.GenreDao;
import ru.otus.shurupov.spring.jpa.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public long count() {
        return genreDao.count();
    }

    @Override
    public Optional<Genre> getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public void insert(String name) {
        genreDao.insert(new Genre(name));
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
