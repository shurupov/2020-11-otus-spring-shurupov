package ru.otus.shurupov.spring.springdatamongodb.mongolistener;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.shurupov.spring.springdatamongodb.repository.BookRepository;

@Component
public class AuthorEventListener extends AbstractMongoEventListener<Object> {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
        Document document = event.getSource();
        String authorId = document.get("_id").toString();
        if (bookRepository.existsByAuthorId(authorId)) {
            throw new RuntimeException("This author has books in the library. Can't be removed.");
        }
    }
}
