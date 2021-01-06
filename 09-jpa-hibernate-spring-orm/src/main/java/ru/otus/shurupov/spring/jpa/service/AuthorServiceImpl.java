package ru.otus.shurupov.spring.jpa.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.jpa.dao.AuthorDao;
import ru.otus.shurupov.spring.jpa.domain.Author;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final TableRenderer tableRenderer;

    @Override
    public long count() {
        return authorDao.count();
    }

    @Override
    public Optional<Author> getById(Long id) {
        return authorDao.getById(id);
    }

    @Override
    @Transactional
    public void insert(String firstName, String lastName) {
        authorDao.insert(new Author(firstName, lastName));
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public void displayList() {
        List<Author> authors = getAll();
        System.out.println(
                tableRenderer.render(
                        "Authors list",
                        Arrays.asList("id", "First Name", "Last Name"),
                        (author) -> Arrays.asList(author.getId().toString(), author.getFirstName(), author.getLastName()),
                        authors
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public void displayById(Long id) {
        Optional<Author> optionalAuthor = getById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            System.out.println(
                    tableRenderer.render(
                            "Author",
                            ImmutableMap.of(
                                    "id", author.getId().toString(),
                                    "First Name", author.getFirstName(),
                                    "Last Name", author.getLastName()
                            )
                    )
            );
        } else {
            System.out.println("Author with id " + id + " not found");
        }
    }

    @Override
    public String getAuthorCaption(Author author) {
        return String.format("%s %s (%s)", author.getFirstName(), author.getLastName(), author.getId());
    }
}
