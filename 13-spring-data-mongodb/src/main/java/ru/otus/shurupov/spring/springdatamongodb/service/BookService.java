package ru.otus.shurupov.spring.springdatamongodb.service;

import ru.otus.shurupov.spring.springdatamongodb.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    void displayList();
}
