package ru.otus.shurupov.spring.hystrix.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "book-author-graph", attributeNodes = @NamedAttributeNode("author"))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    private List<Genre> genres;

    @OneToMany(targetEntity = BookComment.class, fetch = FetchType.LAZY, mappedBy = "book")
    private List<BookComment> comments;

    @Column(name = "name")
    private String name;

    public Book(String name, Author author, List<Genre> genres) {
        this.id = null;
        this.author = author;
        this.genres = new ArrayList<>(genres);
        this.name = name;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
