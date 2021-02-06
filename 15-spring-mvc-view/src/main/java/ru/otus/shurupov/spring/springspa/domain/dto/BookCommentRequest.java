package ru.otus.shurupov.spring.springdata.domain.dto;

import lombok.Data;

@Data
public class BookCommentRequest {
    private Long id;
    private String text;
    private Long bookId;
}
