package ru.otus.shurupov.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.shurupov.spring.integration.service.KitchenService;

@Configuration
public class KitchensConfig {

    @Bean
    public KitchenService coldShop() {
        return new KitchenService(2);
    }

    @Bean
    public KitchenService hotShop() {
        return new KitchenService(1);
    }
}
