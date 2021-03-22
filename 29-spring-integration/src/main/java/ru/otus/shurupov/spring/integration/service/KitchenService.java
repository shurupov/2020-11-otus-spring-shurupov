package ru.otus.shurupov.spring.integration.service;

import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.integration.domain.FoodItem;
import ru.otus.shurupov.spring.integration.domain.OrderItem;

@Service
public class KitchenService {
    public FoodItem cook(OrderItem orderItem) {
        return new FoodItem(orderItem.getTable(), orderItem.getName());
    }
}
