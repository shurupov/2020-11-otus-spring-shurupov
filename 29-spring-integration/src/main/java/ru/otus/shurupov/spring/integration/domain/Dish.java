package ru.otus.shurupov.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Dish {


    GREEK_SALAD("greek salad", DishType.COLD_DISH),
    OLIVIE_SALAD("olivie salad", DishType.COLD_DISH),

    PIZZA("pizza", DishType.HOT_DISH),
    RED_SEAFOOD_SOUP("red seafood soup", DishType.HOT_DISH),

    COFFEE("coffee", DishType.HOT_DRINK),
    TEA("tea", DishType.HOT_DRINK),

    JUICE("juice", DishType.COLD_DRINK),
    SMOOTHIE("smoothie", DishType.COLD_DRINK),
    BEER("BEER", DishType.COLD_DRINK),
    water("water", DishType.COLD_DRINK),

    CAKE("cake", DishType.DESSERT),
    CHEESECAKE("cheesecake", DishType.DESSERT);

    private final String name;
    private final DishType type;

    public static Dish fromString(String name) {
        for (Dish dish : values()) {
            if (dish.name.equals(name)) {
                return dish;
            }
        }
        return null;
    }
}
