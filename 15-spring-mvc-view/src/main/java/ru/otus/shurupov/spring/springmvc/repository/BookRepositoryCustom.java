package ru.otus.shurupov.spring.springmvc.repository;

import java.util.List;

public interface BookRepositoryCustom {
    void setGenres(Long bookId, List<Long> genreIds);
}
