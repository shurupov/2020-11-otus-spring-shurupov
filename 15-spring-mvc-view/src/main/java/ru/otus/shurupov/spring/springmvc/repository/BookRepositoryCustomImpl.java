package ru.otus.shurupov.spring.springmvc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.shurupov.spring.springmvc.domain.Book;
import ru.otus.shurupov.spring.springmvc.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public void setGenres(Long bookId, List<Long> genreIds) {
        Book book = entityManager.find(Book.class, bookId);
        List<Genre> genres = genreRepository.findAllById(genreIds);
        book.setGenres(genres);
        entityManager.merge(book);
    }
}
