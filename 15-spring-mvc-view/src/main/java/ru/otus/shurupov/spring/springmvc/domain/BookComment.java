package ru.otus.shurupov.spring.springmvc.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_comment")
@NamedEntityGraph(name = "comment-book-graph", attributeNodes = @NamedAttributeNode("book"))
public class BookComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "comment_text")
    private String text;
}
