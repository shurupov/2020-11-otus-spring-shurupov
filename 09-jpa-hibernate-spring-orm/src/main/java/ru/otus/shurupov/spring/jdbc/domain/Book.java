package ru.otus.shurupov.spring.jdbc.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /*@ManyToOne(targetEntity = Author.class, cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(targetEntity = Author.class, cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Genre genre;*/

    @Column(name = "name")
    private String name;

    /*public Book(String name, Author author, Genre genre) {
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
    }*/
}
