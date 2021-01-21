package ru.otus.shurupov.spring.springdatamongodb.service;

import ru.otus.shurupov.spring.springdatamongodb.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    long count();
    Optional<Author> getById(String id);
    void insert(String firstName, String lastName);
    List<Author> getAll();

    void displayList();
    void displayById(String id);

    String getAuthorCaption(Author author);
}
