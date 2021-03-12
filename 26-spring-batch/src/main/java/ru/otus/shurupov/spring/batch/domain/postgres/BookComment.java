package ru.otus.shurupov.spring.batch.domain.postgres;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_comment")
public class BookComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = PostgresBook.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private PostgresBook book;

    @Column(name = "comment_text")
    private String text;
}
