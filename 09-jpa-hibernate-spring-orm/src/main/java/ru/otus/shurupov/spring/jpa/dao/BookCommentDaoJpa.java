package ru.otus.shurupov.spring.jpa.dao;

import org.springframework.stereotype.Repository;
import ru.otus.shurupov.spring.jpa.domain.BookComment;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookCommentDaoJpa implements BookCommentDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(c) from BookComment c", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Optional<BookComment> getById(long id) {
        return Optional.ofNullable(em.find(BookComment.class, id));
    }

    @Override
    public void insert(BookComment comment) {
        em.persist(comment);
    }

    @Override
    public List<BookComment> getAll() {
        TypedQuery<BookComment> query = em.createQuery("select c from BookComment c", BookComment.class);
        EntityGraph<?> entityGraph = em.getEntityGraph("comment-book-graph");
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void remove(BookComment bookComment) {
        em.remove(bookComment);
    }

    @Override
    public void updateById(long id, String comment) {
        BookComment bookComment = em.find(BookComment.class, id);
        bookComment.setText(comment);
        em.merge(bookComment);
    }
}
