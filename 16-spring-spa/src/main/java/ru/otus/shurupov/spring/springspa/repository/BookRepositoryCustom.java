package ru.otus.shurupov.spring.springspa.repository;

import java.util.List;

public interface BookRepositoryCustom {
    void setGenres(Long bookId, List<Long> genreIds);
}
