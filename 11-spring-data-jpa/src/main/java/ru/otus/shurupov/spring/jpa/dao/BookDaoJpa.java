package ru.otus.shurupov.spring.jpa.dao;

import org.springframework.stereotype.Repository;
import ru.otus.shurupov.spring.jpa.domain.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoJpa implements BookDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(b) from Book b", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public void insert(Book book) {
        em.persist(book);
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        EntityGraph<?> entityGraph = em.getEntityGraph("book-author-graph");
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void remove(Book book) {
        em.remove(book);
    }

    @Override
    public void updateNameById(long id, String name) {
        Book book = em.find(Book.class, id);
        book.setName(name);
        em.merge(book);
    }
}
