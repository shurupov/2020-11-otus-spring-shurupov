package ru.otus.shurupov.spring.batch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.shurupov.spring.batch.domain.mongo.MongoAuthor;

public interface AuthorMongoRepository extends MongoRepository<MongoAuthor, String> {
}
