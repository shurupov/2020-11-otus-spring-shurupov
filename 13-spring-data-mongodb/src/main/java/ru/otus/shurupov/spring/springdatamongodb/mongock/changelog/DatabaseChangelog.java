package ru.otus.shurupov.spring.springdatamongodb.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.shurupov.spring.springdatamongodb.domain.Author;
import ru.otus.shurupov.spring.springdatamongodb.domain.Book;
import ru.otus.shurupov.spring.springdatamongodb.repository.AuthorRepository;
import ru.otus.shurupov.spring.springdatamongodb.repository.BookRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {
    @ChangeSet(order = "002", id = "insertBooks", author = "shurupov")
    public void insertBooks(BookRepository bookRepository, AuthorRepository authorRepository) {
        Book book1 = new Book();
        book1.setName("Book 1");
        Author author1 = new Author();
        author1.setFirstName("Alexander");
        author1.setLastName("Pushkin");
        author1 = authorRepository.save(author1);
        book1.setAuthor(author1);
        book1.setGenres(List.of("Horror", "Comedy"));
        bookRepository.save(book1);
        Book book2 = new Book();
        book2.setAuthor(author1);
        book2.setGenres(List.of("Drama", "Tragedy"));
        book2.setName("Book 2");
        bookRepository.save(book2);
        Book book3 = new Book();
        Author author2 = new Author();
        author2.setFirstName("Haruki");
        author2.setLastName("Murakami");
        author2 = authorRepository.save(author2);
        book3.setAuthor(author2);
        book3.setGenres(List.of());
        book3.setName("Book 3");
        bookRepository.save(book3);
    }
}
