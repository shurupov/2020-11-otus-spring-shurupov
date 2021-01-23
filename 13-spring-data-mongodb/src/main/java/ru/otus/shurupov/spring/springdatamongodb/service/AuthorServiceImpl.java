package ru.otus.shurupov.spring.springdatamongodb.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.springdatamongodb.domain.Author;
import ru.otus.shurupov.spring.springdatamongodb.repository.AuthorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final TableRenderer tableRenderer;

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    public Optional<Author> getById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public void insert(String firstName, String lastName) {
        authorRepository.save(new Author(firstName, lastName));
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public void displayList() {
        List<Author> authors = getAll();
        System.out.println(
                tableRenderer.render(
                        "Authors list",
                        Arrays.asList("id", "First Name", "Last Name"),
                        (author) -> Arrays.asList(author.getId(), author.getFirstName(), author.getLastName()),
                        authors
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public void displayById(String id) {
        Optional<Author> optionalAuthor = getById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            System.out.println(
                    tableRenderer.render(
                            "Author",
                            ImmutableMap.of(
                                    "id", author.getId(),
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
    public void removeById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    public String getAuthorCaption(Author author) {
        return String.format("%s %s", author.getFirstName(), author.getLastName());
    }
}
