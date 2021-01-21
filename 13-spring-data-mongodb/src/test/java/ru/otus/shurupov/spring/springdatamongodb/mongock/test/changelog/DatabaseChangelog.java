package ru.otus.shurupov.spring.springdatamongodb.mongock.test.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.shurupov.spring.springdatamongodb.domain.Author;
import ru.otus.shurupov.spring.springdatamongodb.domain.Book;
import ru.otus.shurupov.spring.springdatamongodb.repository.AuthorRepository;
import ru.otus.shurupov.spring.springdatamongodb.repository.BookRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {
    @ChangeSet(order = "001", id = "authors", author = "shurupov")
    public void authors(AuthorRepository authorRepository) {
        authorRepository.save(new Author("1", "Arthur", "Conan Doyle"));
        authorRepository.save(new Author("2", "Agatha", "Christie"));
        authorRepository.save(new Author("3", "Alexander", "Pushkin"));
        authorRepository.save(new Author("4", "Fedor", "Dostoevsky"));
    }

    @ChangeSet(order = "002", id = "books", author = "shurupov")
    public void books(BookRepository bookRepository, AuthorRepository authorRepository) {
        bookRepository.save(
                new Book(
                        authorRepository.findById("1").get(),
                        "Sherlock Holmes. A Study in Scarlet",
                        List.of("Crime and Detective")
                )
        );
        bookRepository.save(
                new Book(
                        authorRepository.findById("2").get(),
                        "Hercule Poirot. The Mysterious Affair at Styles",
                        List.of("Crime and Detective", "Fairy Tale")
                )
        );
        bookRepository.save(
                new Book(
                        authorRepository.findById("3").get(),
                        "The Tale about a Fisherman and a Fish",
                        List.of("Fairy Tale")
                )
        );
        bookRepository.save(
                new Book(
                        authorRepository.findById("4").get(),
                        "Crime and Punishment",
                        List.of("Drama")
                )
        );
    }
}
