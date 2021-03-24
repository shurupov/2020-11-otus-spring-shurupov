package ru.otus.shurupov.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.shurupov.spring.integration.service.DrinksKitchenService;
import ru.otus.shurupov.spring.integration.service.KitchenService;

@Configuration
public class KitchensConfig {

    @Bean
    public KitchenService coldShop() {
        return new KitchenService(2);
    }

    @Bean
    public KitchenService hotShop() {
        return new KitchenService(3);
    }

    @Bean
    public KitchenService drinksShop() {
        return new DrinksKitchenService(1);
    }

    @Bean
    public KitchenService pastryShop() {
        return new KitchenService(3);
    }
}
