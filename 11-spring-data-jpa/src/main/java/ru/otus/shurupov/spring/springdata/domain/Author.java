package ru.otus.shurupov.spring.springdata.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Author(String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Author(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Author() {
    }
}
