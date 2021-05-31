package ru.otus.shurupov.jointpurchase.domain;

import lombok.Data;
import ru.otus.shurupov.jointpurchase.converter.UserRoleListConverter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "c_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    @Convert(converter = UserRoleListConverter.class)
    private List<UserRole> roles;
}
