package ru.otus.shurupov.spring.jpa.domain;

import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "book-author-graph", attributeNodes = @NamedAttributeNode("author"))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    private List<Genre> genres;

    @OneToMany(targetEntity = BookComment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<BookComment> comments;

    @Column(name = "name")
    private String name;

    public Book(String name, Author author, Genre genre) {
        this(name, author, Collections.singletonList(genre));
    }

    public Book(String name, Author author, List<Genre> genres) {
        this.id = null;
        this.author = author;
        this.genres = new ArrayList<>(genres);
        this.name = name;
    }

    public Book(Long id, Author author, Genre genre, String name) {
        this(id, author, Collections.singletonList(genre), name);
    }

    public Book(Long id, Author author, List<Genre> genres, String name) {
        this.id = id;
        this.author = author;
        this.genres = new ArrayList<>(genres);
        this.name = name;
    }

    public Book() {
    }

    public String getAuthorCaption() {
        return String.format("%s %s (%s)", author.getFirstName(), author.getLastName(), author.getId());
    }

    public String getGenreCaption() {
        return genres.stream()
                .map(genre -> String.format("%s (%s)", genre.getName(), genre.getId()))
                .collect(Collectors.joining(", "));
    }
}
