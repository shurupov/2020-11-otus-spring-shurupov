package ru.otus.shurupov.spring.jdbc.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    private final Long id;
    private final String name;
}
