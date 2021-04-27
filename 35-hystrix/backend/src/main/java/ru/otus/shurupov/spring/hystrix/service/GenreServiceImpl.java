package ru.otus.shurupov.spring.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;
import ru.otus.shurupov.spring.hystrix.repository.GenreRepository;
import ru.otus.shurupov.spring.hystrix.domain.Genre;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private Map<Long, Genre> genresCache = new HashMap<>();

    @Override
    @HystrixCommand(commandKey="getGenres", fallbackMethod="countFallback")
    public long count() {
        return genreRepository.count();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @HystrixCommand(commandKey="getGenres", fallbackMethod="getByIdFromCache")
    public Genre getById(Long id) {
        return genreRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @HystrixCommand(commandKey="getGenres", fallbackMethod="throwException")
    public Genre insert(Genre genre) {
        if (genre.getId() != null) {
            genre.setId(null);
        }
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @HystrixCommand(commandKey="getGenres", fallbackMethod="throwException")
    public Genre update(Long id, Genre genre) {
        Genre genre1 = genreRepository.findById(id).orElseThrow();
        genre1.setName(genre.getName());
        return genreRepository.save(genre);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @HystrixCommand(commandKey="getGenres", fallbackMethod="throwException")
    public void removeById(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    @HystrixCommand(commandKey="getGenres", fallbackMethod="getCached")
    public Collection<Genre> getAll() {
        List<Genre> result = genreRepository.findAll(Sort.by("id"));
        genresCache = result.stream().collect(Collectors.toMap(
                Genre::getId,
                g -> g
        ));
        return result;
    }

    public Collection<Genre> getCached() {
        return genresCache.values();
    }

    public Genre getByIdFromCache(Long id) {
        return genresCache.get(id);
    }

    public void throwException() {
        throw new ServerErrorException("Temporary server problems");
    }

    public long countFallback() {
        return 0L;
    }
}
