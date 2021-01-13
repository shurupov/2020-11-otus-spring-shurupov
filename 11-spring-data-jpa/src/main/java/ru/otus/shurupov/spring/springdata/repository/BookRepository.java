package ru.otus.shurupov.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.otus.shurupov.spring.springdata.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("update Book b set b.name = :name where b.id = :id")
    @Modifying
    void updateNameById(Long id, String name);

    List<Book> findByAuthorFirstNameContainingIgnoreCaseOrAuthorLastNameContainingIgnoreCase(String firstname, String lastname);

    List<Book> findByNameContainingIgnoreCase(String name);
}
