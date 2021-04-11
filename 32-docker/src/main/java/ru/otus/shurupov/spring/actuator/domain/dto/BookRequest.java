package ru.otus.shurupov.spring.actuator.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookRequest {
    private Long id;
    private String name;
    private Long authorId;
    private List<Long> genres;
}
