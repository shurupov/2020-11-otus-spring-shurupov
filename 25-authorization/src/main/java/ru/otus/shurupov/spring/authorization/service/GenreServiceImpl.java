package ru.otus.shurupov.spring.authorization.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.authorization.repository.GenreRepository;
import ru.otus.shurupov.spring.authorization.domain.Genre;

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
    public Genre insert(Genre genre) {
        if (genre.getId() != null) {
            genre.setId(null);
        }
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public Genre update(Long id, Genre genre) {
        Genre genre1 = genreRepository.findById(id).orElseThrow();
        genre1.setName(genre.getName());
        return genreRepository.save(genre);
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
