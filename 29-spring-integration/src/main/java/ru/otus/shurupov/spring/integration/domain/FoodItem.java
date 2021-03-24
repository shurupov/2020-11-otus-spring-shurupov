package ru.otus.shurupov.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodItem {
    private int table;
    private String name;
}
