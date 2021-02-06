package ru.otus.shurupov.spring.springspa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDtoForList {
    private Long id;
    private String name;
    private String author;
    private String genres;
}
