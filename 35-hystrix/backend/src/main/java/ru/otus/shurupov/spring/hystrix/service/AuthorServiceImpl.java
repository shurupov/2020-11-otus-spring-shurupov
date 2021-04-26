package ru.otus.shurupov.spring.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;
import ru.otus.shurupov.spring.hystrix.repository.AuthorRepository;
import ru.otus.shurupov.spring.hystrix.domain.Author;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private Map<Long, Author> cache = Collections.emptyMap();

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    @HystrixCommand(commandKey="getAuthors", fallbackMethod="getByIdFromCache")
    public Author getById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey="getAuthors", fallbackMethod="throwException")
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @HystrixCommand(commandKey="getAuthors", fallbackMethod="getAllCached")
    public Collection<Author> getAll() {
        List<Author> result = authorRepository.findAll(Sort.by("id"));
        updateCache(result);
        return result;
    }

    @Override
    @HystrixCommand(commandKey="getAuthors", fallbackMethod="throwException")
    public void removeById(Long id) {
        authorRepository.deleteById(id);
    }

    public void updateCache(Collection<Author> source) {
        cache = source.stream().collect(Collectors.toMap(
                Author::getId, author -> author
        ));
    }

    public Collection<Author> getAllCached() {
        return cache.values();
    }

    public Author getByIdFromCache(Long id) {
        return cache.get(id);
    }

    public void throwException() {
        throw new ServerErrorException("Temporary server problems", new RuntimeException());
    }
}
