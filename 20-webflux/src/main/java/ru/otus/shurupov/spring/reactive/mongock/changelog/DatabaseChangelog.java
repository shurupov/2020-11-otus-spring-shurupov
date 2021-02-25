package ru.otus.shurupov.spring.reactive.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.shurupov.spring.reactive.domain.Author;
import ru.otus.shurupov.spring.reactive.domain.Book;
import ru.otus.shurupov.spring.reactive.mongock.repository.AuthorSyncRepository;
import ru.otus.shurupov.spring.reactive.mongock.repository.BookSyncRepository;

import java.util.List;

@ChangeLog(order = "001")
public class DatabaseChangelog {
    @ChangeSet(order = "001", id = "authors", author = "shurupov")
    public void authors(AuthorSyncRepository authorSyncRepository) {
        authorSyncRepository.save(new Author("1", "Arthur", "Conan Doyle"));
        authorSyncRepository.save(new Author("2", "Agatha", "Christie"));
        authorSyncRepository.save(new Author("3", "Alexander", "Pushkin"));
        authorSyncRepository.save(new Author("4", "Fedor", "Dostoevsky"));
    }

    @ChangeSet(order = "002", id = "books", author = "shurupov")
    public void books(BookSyncRepository bookSyncRepository, AuthorSyncRepository authorSyncRepository) {
        bookSyncRepository.save(
                new Book(
                        authorSyncRepository.findById("1").get(),
                        "Sherlock Holmes. A Study in Scarlet",
                        List.of("Crime and Detective"),
                        List.of("Interesting")
                )
        );
        bookSyncRepository.save(
                new Book(
                        authorSyncRepository.findById("1").get(),
                        "The lost world",
                        List.of("Action and Adventure", "Science Fiction"),
                        List.of("Weird, it's about dinosaurs, but written in 19th century.", "Cool!")
                )
        );
        bookSyncRepository.save(
                new Book(
                        authorSyncRepository.findById("2").get(),
                        "Hercule Poirot. The Mysterious Affair at Styles",
                        List.of("Crime and Detective", "Fairy Tale"),
                        List.of("One more detective from Agatha Cristie")
                )
        );
        bookSyncRepository.save(
                new Book(
                        authorSyncRepository.findById("3").get(),
                        "The Tale about a Fisherman and a Fish",
                        List.of("Fairy Tale"),
                        List.of("I like to read this for my kids!", "Grown-ups can get useful things there, too.")
                )
        );
        bookSyncRepository.save(
                new Book(
                        authorSyncRepository.findById("3").get(),
                        "Ruslan and Ludmila",
                        List.of("Fairy Tale"),
                        List.of("It's about love")
                )
        );
        bookSyncRepository.save(
                new Book(
                        authorSyncRepository.findById("4").get(),
                        "Crime and Punishment",
                        List.of("Drama"),
                        List.of("Very big book.", "Too much text.")
                )
        );
        bookSyncRepository.save(
                new Book(
                        authorSyncRepository.findById("4").get(),
                        "Player",
                        List.of("Drama"),
                        List.of("Don't play cards on big money.")
                )
        );
    }
}
