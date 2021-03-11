package ru.otus.shurupov.spring.authorization.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoorBookResponse {
    private Long id;
    private String name;
    private Long authorId;
    private List<Long> genres;
}
