package ru.otus.shurupov.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
public class ColdShopConfig {

    @Bean
    public QueueChannel coldShopChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public IntegrationFlow coldShopFlow() {
        return IntegrationFlows.from("coldShopChannel")
                .handle("kitchenService", "cook")
                .channel("readyFoodChannel")
                .get();
    }
}
