package ru.otus.shurupov.spring.hystrix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.hystrix.repository.GenreRepository;
import ru.otus.shurupov.spring.hystrix.domain.Genre;

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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Genre getById(Long id) {
        return genreRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Genre insert(Genre genre) {
        if (genre.getId() != null) {
            genre.setId(null);
        }
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Genre update(Long id, Genre genre) {
        Genre genre1 = genreRepository.findById(id).orElseThrow();
        genre1.setName(genre.getName());
        return genreRepository.save(genre);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeById(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll(Sort.by("id"));
    }
}
