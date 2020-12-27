package ru.otus.shurupov.spring.jpa.dao;

import org.springframework.stereotype.Repository;
import ru.otus.shurupov.spring.jpa.domain.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoJdbc implements BookDao {
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
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.genre", Book.class);
        EntityGraph<?> entityGraph = em.getEntityGraph("book-author-graph");
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void removeById(Long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update Book b set b.name = :name where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }
}
