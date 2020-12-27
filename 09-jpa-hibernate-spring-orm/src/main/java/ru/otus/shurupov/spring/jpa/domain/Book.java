package ru.otus.shurupov.spring.jpa.domain;

import lombok.Data;

import javax.persistence.*;

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

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "name")
    private String name;

    public Book(String name, Author author, Genre genre) {
        this.id = null;
        this.author = author;
        this.genre = genre;
        this.name = name;
    }

    public Book(Long id, Author author, Genre genre, String name) {
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.name = name;
    }

    public Book() {
    }

    public String getAuthorCaption() {
        return String.format("%s %s (%s)", author.getFirstName(), author.getLastName(), author.getId());
    }

    public String getGenreCaption() {
        return String.format("%s (%s)", genre.getName(), genre.getId());
    }
}
