package ru.otus.shurupov.spring.batch.domain.postgres;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "book")
public class PostgresBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = PostgresAuthor.class)
    @JoinColumn(name = "author_id")
    private PostgresAuthor author;

    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    private List<Genre> genres;

    @OneToMany(targetEntity = BookComment.class, fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<BookComment> comments;

    @Column(name = "name")
    private String name;

    public PostgresBook() {
    }

    @Override
    public String toString() {
        return "PostgresBook{" +
                "id=" + id +
                ", author=" + author +
                ", genres=" + genres +
                ", comments=" + comments.stream().map(BookComment::getText).collect(Collectors.toList()) +
                ", name='" + name + '\'' +
                '}';
    }
}
