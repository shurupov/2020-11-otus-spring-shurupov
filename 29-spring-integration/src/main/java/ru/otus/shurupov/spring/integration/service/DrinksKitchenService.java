package ru.otus.shurupov.spring.integration.service;

import ru.otus.shurupov.spring.integration.domain.Dish;
import ru.otus.shurupov.spring.integration.domain.DishType;

public class DrinksKitchenService extends KitchenService {
    public DrinksKitchenService(long cookingTime) {
        super(cookingTime);
    }

    @Override
    protected long getCookingTime(String dishName) {
        DishType dishType = Dish.fromString(dishName).getType();
        return dishType.equals(DishType.HOT_DRINK)
                ? super.getCookingTime(dishName) * 3
                : super.getCookingTime(dishName);
    }
}
