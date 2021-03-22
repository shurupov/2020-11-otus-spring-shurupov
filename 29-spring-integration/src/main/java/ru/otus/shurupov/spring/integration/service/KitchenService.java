package ru.otus.shurupov.spring.integration.service;

import lombok.AllArgsConstructor;
import ru.otus.shurupov.spring.integration.domain.FoodItem;
import ru.otus.shurupov.spring.integration.domain.OrderItem;

@AllArgsConstructor
public class KitchenService {

    private final long cookingTime;

    public FoodItem cook(OrderItem orderItem) throws InterruptedException {
        System.out.printf("Started cooking %s for table %s.%n", orderItem.getName(), orderItem.getTable());
        Thread.sleep(cookingTime * 1000);
        System.out.printf("Cooking %s for table %s completed in %s seconds.%n", orderItem.getName(), orderItem.getTable(), (cookingTime));
        return new FoodItem(orderItem.getTable(), orderItem.getName());
    }
}
