package ru.otus.shurupov.spring.springdatamongodb.service;

import ru.otus.shurupov.spring.springdatamongodb.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    long count();
    Optional<Book> getById(String id);
    void insert(String name, String authorId, List<String> genres);
    List<Book> getAll();
    void removeById(String id);

    void displayList();
    void displayById(String id);

    void displayByNameFilteredList(String name);
}
