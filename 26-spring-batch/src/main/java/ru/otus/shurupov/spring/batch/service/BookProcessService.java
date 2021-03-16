package ru.otus.shurupov.spring.batch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.batch.domain.mongo.MongoAuthor;
import ru.otus.shurupov.spring.batch.domain.mongo.MongoBook;
import ru.otus.shurupov.spring.batch.domain.postgres.BookComment;
import ru.otus.shurupov.spring.batch.domain.postgres.Genre;
import ru.otus.shurupov.spring.batch.domain.postgres.PostgresAuthor;
import ru.otus.shurupov.spring.batch.domain.postgres.PostgresBook;
import ru.otus.shurupov.spring.batch.repository.AuthorRepository;
import ru.otus.shurupov.spring.batch.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookProcessService {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public PostgresBook process(MongoBook mongoBook) {
        PostgresBook outputBook = new PostgresBook();
        outputBook.setName(mongoBook.getName());
        outputBook.setAuthor(getOrCreateAuthor(mongoBook.getAuthor()));
        outputBook.setGenres(getOrCreateGenres(mongoBook.getGenres()));
        outputBook.setComments(createComments(mongoBook.getComments(), outputBook));
        return outputBook;
    }

    private PostgresAuthor getOrCreateAuthor(MongoAuthor mongoAuthor) {
        Optional<PostgresAuthor> authorOptional = authorRepository.findByFirstNameAndLastName(
                mongoAuthor.getFirstName(),
                mongoAuthor.getLastName()
        );
        if (authorOptional.isPresent()) {
            return authorOptional.get();
        } else {
            PostgresAuthor author = new PostgresAuthor(
                    mongoAuthor.getFirstName(),
                    mongoAuthor.getLastName()
            );
            return authorRepository.save(author);
        }
    }

    private List<Genre> getOrCreateGenres(List<String> names) {
        List<Genre> genres = new ArrayList<>(names.size());
        genres.addAll(genreRepository.findAllByNameIn(names));

        for (String name : names) {
            if (genres.stream().noneMatch(g -> g.getName().equals(name))) {
                Genre genre = genreRepository.save(new Genre(name));
                genres.add(genre);
            }
        }
        return genres;
    }

    private List<BookComment> createComments(List<String> contents, PostgresBook book) {
        if (contents == null) {
            return List.of();
        }
        List<BookComment> comments = new ArrayList<>(contents.size());
        for (String text : contents) {
            BookComment comment = new BookComment();
            comment.setText(text);
            comment.setBook(book);
            comments.add(comment);
        }
        return comments;
    }
}
