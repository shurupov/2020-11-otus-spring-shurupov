package ru.otus.shurupov.spring.batch.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.shurupov.spring.batch.domain.mongo.MongoAuthor;
import ru.otus.shurupov.spring.batch.domain.mongo.MongoBook;
import ru.otus.shurupov.spring.batch.repository.AuthorMongoRepository;
import ru.otus.shurupov.spring.batch.repository.BookMongoRepository;

import java.util.List;

@ChangeLog(order = "001")
public class DatabaseChangelog {
    @ChangeSet(order = "001", id = "authors", author = "shurupov")
    public void authors(AuthorMongoRepository authorMongoRepository) {
        authorMongoRepository.save(new MongoAuthor("1", "Arthur", "Conan Doyle"));
        authorMongoRepository.save(new MongoAuthor("2", "Agatha", "Christie"));
        authorMongoRepository.save(new MongoAuthor("3", "Alexander", "Pushkin"));
        authorMongoRepository.save(new MongoAuthor("4", "Fedor", "Dostoevsky"));
    }

    @ChangeSet(order = "002", id = "books", author = "shurupov")
    public void books(BookMongoRepository bookMongoRepository, AuthorMongoRepository authorMongoRepository) {
        bookMongoRepository.save(
                new MongoBook(
                        authorMongoRepository.findById("1").get(),
                        "Sherlock Holmes. A Study in Scarlet",
                        List.of("Crime and Detective"),
                        List.of("Interesting")
                )
        );
        bookMongoRepository.save(
                new MongoBook(
                        authorMongoRepository.findById("1").get(),
                        "The lost world",
                        List.of("Action and Adventure", "Science Fiction"),
                        List.of("Weird, it's about dinosaurs, but written in 19th century.", "Cool!")
                )
        );
        bookMongoRepository.save(
                new MongoBook(
                        authorMongoRepository.findById("2").get(),
                        "Hercule Poirot. The Mysterious Affair at Styles",
                        List.of("Crime and Detective", "Fairy Tale"),
                        List.of("One more detective from Agatha Cristie")
                )
        );
        bookMongoRepository.save(
                new MongoBook(
                        authorMongoRepository.findById("3").get(),
                        "The Tale about a Fisherman and a Fish",
                        List.of("Fairy Tale"),
                        List.of("I like to read this for my kids!", "Grown-ups can get useful things there, too.")
                )
        );
        bookMongoRepository.save(
                new MongoBook(
                        authorMongoRepository.findById("3").get(),
                        "Ruslan and Ludmila",
                        List.of("Fairy Tale"),
                        List.of("It's about love")
                )
        );
        bookMongoRepository.save(
                new MongoBook(
                        authorMongoRepository.findById("4").get(),
                        "Crime and Punishment",
                        List.of("Drama"),
                        List.of("Very big book.", "Too much text.")
                )
        );
        bookMongoRepository.save(
                new MongoBook(
                        authorMongoRepository.findById("4").get(),
                        "Player",
                        List.of("Drama"),
                        List.of("Don't play cards on big money.")
                )
        );
    }
}
