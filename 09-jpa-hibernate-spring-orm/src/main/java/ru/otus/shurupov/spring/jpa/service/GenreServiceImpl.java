package ru.otus.shurupov.spring.jpa.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.jpa.dao.GenreDao;
import ru.otus.shurupov.spring.jpa.domain.Genre;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final TableRenderer tableRenderer;

    @Override
    public long count() {
        return genreDao.count();
    }

    @Override
    public Optional<Genre> getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    @Transactional
    public void insert(String name) {
        genreDao.insert(new Genre(name));
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public void displayList() {
        List<Genre> genres = getAll();
        System.out.println(
                tableRenderer.render(
                        "Genres list",
                        Arrays.asList("id", "Genre name"),
                        (genre) -> Arrays.asList(genre.getId().toString(), genre.getName()),
                        genres
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public void displayById(Long id) {
        Optional<Genre> optionalGenre = getById(id);
        if (optionalGenre.isPresent()) {
            Genre genre = optionalGenre.get();
            System.out.println(
                    tableRenderer.render(
                            "Genre",
                            ImmutableMap.of(
                                    "id", genre.getId(),
                                    "Name", genre.getName()
                            )

                    )
            );
        } else {
            System.out.println("Genre with id " + id + " not found");
        }
    }

    @Override
    public String getGenreCaption(List<Genre> genres) {
        return genres.stream()
                .map(genre -> String.format("%s (%s)", genre.getName(), genre.getId()))
                .collect(Collectors.joining(", "));
    }
}
