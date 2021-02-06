package ru.otus.shurupov.spring.springspa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.springspa.repository.GenreRepository;
import ru.otus.shurupov.spring.springspa.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public long count() {
        return genreRepository.count();
    }

    @Override
    public Genre getById(Long id) {
        return genreRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void insert(String name) {
        genreRepository.save(new Genre(name));
    }

    @Override
    @Transactional
    public void update(Long id, String name) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        genre.setName(name);
        genreRepository.save(genre);
    }

    @Override
    public void removeById(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll(Sort.by("id"));
    }
}
