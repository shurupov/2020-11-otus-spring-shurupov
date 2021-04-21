package ru.otus.shurupov.spring.hystrix.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCommentDto {
    private Long id;
    private String text;
}
