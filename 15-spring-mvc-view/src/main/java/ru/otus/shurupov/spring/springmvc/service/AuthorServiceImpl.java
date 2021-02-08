package ru.otus.shurupov.spring.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.springmvc.repository.AuthorRepository;
import ru.otus.shurupov.spring.springmvc.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll(Sort.by("id"));
    }

    @Override
    public void removeById(Long id) {
        authorRepository.deleteById(id);
    }
}
