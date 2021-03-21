package ru.otus.shurupov.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodItem {
    private int tableNumber;
    private String name;
}
