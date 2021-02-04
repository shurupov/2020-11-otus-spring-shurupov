package ru.otus.shurupov.spring.springdata.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookRequest {
    private Long id;
    private String name;
    private Long authorId;
    private List<Long> genreIds;
}
