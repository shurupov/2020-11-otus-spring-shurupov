package ru.otus.shurupov.spring.springdata.repository;

import java.util.List;

public interface BookRepositoryCustom {
    void setGenres(Long bookId, List<Long> genreIds);
}
