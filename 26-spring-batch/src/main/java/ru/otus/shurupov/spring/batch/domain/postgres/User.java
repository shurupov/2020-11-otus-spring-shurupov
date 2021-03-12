package ru.otus.shurupov.spring.batch.domain.postgres;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
