package ru.otus.shurupov.spring.springmvc.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookRequest {
    private Long id;
    private String name;
    private Long authorId;
    private List<Long> genreIds;
}
