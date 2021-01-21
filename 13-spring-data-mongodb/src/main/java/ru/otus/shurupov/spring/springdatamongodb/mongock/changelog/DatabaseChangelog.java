package ru.otus.shurupov.spring.springdatamongodb.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.otus.shurupov.spring.springdatamongodb.domain.Book;
import ru.otus.shurupov.spring.springdatamongodb.repository.BookRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "shurupov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    /*@ChangeSet(order = "002", id = "insertLermontov", author = "ydvorzhetskiy")
    public void insertLermontov(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("persons");
        var doc = new Document().append("name", "Lermontov");
        myCollection.insertOne(doc);
    }*/

    @ChangeSet(order = "002", id = "insertBooks", author = "shurupov")
    public void insertPushkin(BookRepository bookRepository) {
        Book book1 = new Book();
        book1.setName("Book 1");
        book1.setGenres(List.of("Horror", "Comedy"));
        bookRepository.save(book1);
        Book book2 = new Book();
        book2.setGenres(List.of("Drama", "Tragedy"));
        book2.setName("Book 2");
        bookRepository.save(book2);
        Book book3 = new Book();
        book3.setGenres(List.of());
        book3.setName("Book 3");
        bookRepository.save(book3);
    }
}
